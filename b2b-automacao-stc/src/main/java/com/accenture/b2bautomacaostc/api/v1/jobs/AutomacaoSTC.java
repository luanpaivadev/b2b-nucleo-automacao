package com.accenture.b2bautomacaostc.api.v1.jobs;

import com.accenture.b2bautomacaostc.Utils.Util;
import com.accenture.b2bautomacaostc.api.v1.assembler.DadosChecklistModelMapper;
import com.accenture.b2bautomacaostc.api.v1.dto.ConsultaDTO;
import com.accenture.b2bautomacaostc.api.v1.dto.DadosSaidaDTO;
import com.accenture.b2bautomacaostc.core.queue.FilaConsultasSTCConfig;
import com.accenture.b2bautomacaostc.domain.model.Consulta;
import com.accenture.b2bautomacaostc.domain.model.DadosChecklist;
import com.accenture.b2bautomacaostc.domain.model.Usuario;
import com.accenture.b2bautomacaostc.domain.repository.ConsultasRepository;
import com.accenture.b2bautomacaostc.domain.repository.DadosChecklistRepository;
import com.accenture.b2bautomacaostc.domain.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AutomacaoSTC {

    @Autowired
    private Environment environment;

    @Autowired
    private DadosChecklistModelMapper dadosChecklistModelMapper;

    @Autowired
    private DadosChecklistRepository dadosChecklistRepository;

    @Autowired
    private ConsultasRepository consultasRepository;

    @Autowired
    private DadosChecklistService dadosChecklistService;

    @Autowired
    private ConsultasService consultasService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private EnvioCallbackService envioCallbackService;

    @Autowired
    private EnvioLogFuncionalService envioLogFuncionalService;

    @Autowired
    private EnvioLogMonitoriaService envioLogMonitoriaService;

    @Autowired
    private Util util;

    @RabbitListener(bindings = @QueueBinding(value = @org.springframework.amqp.rabbit.annotation.Queue(FilaConsultasSTCConfig.QUEUE_NAME),
            exchange = @Exchange(name = FilaConsultasSTCConfig.EXCHANGE_NAME),
            key = FilaConsultasSTCConfig.ROUTING_KEY))
    public void executaConsultasPendentesParaSistemaSTC(DadosChecklist dadosChecklist) {

        Usuario usuario = null;
        final var dadosSaidaDTO = new DadosSaidaDTO();
        final var consultaDTO = new LinkedList<ConsultaDTO>();
        final var UF = dadosChecklist.getUf();
        final var UG = util.getUgSTC(UF);

        try {
            usuario = loginService.alocarUsuarioDisponivel(UF);

            Iterable<Long> ids = dadosChecklist.getConsultas()
                    .stream()
                    .map(Consulta::getId)
                    .collect(Collectors.toList());

            var consultas = consultasRepository.findAllById(ids);

            log.info("\nIniciando consulta no sistema STC...");
            for (Consulta consulta : consultas) {

                consulta.setUsuario(usuario.getUsuario());
                consulta.setPortaAutomacao(environment.getProperty("local.server.port"));

                switch (consulta.getDescricao().toLowerCase()) {
                    case "ddr":
                        log.info("Iniciando consulta de DDR...");
                        Thread.sleep(2000);
                        consultaDTO.add(consultasService.consultaDDR(consulta));
                        break;
                    case "reparo":
                        log.info("Iniciando consulta de Reparo...");
                        Thread.sleep(2000);
                        consultaDTO.add(consultasService.consultaReparo(consulta));
                        break;
                    case "bloqueio":
                        log.info("Iniciando consulta de Bloqueio...");
                        Thread.sleep(2000);
                        consultaDTO.add(consultasService.consultaBloqueio(consulta));
                        break;
                    case "os":
                        log.info("Iniciando consulta de OS...");
                        Thread.sleep(2000);
                        consultaDTO.add(consultasService.consultaOs(consulta));
                        break;
                    case "osdespachada":
                        log.info("Iniciando consulta de OS Despachada...");
                        Thread.sleep(2000);
                        consultaDTO.add(consultasService.consultaOsDespachada(consulta));
                        break;
                    default:
                        break;
                }
            }

            dadosSaidaDTO.setDadosChecklist(dadosChecklistModelMapper.toDTOObject(dadosChecklist));
            dadosSaidaDTO.setConsultas(consultaDTO);

            var enviado = envioCallbackService.enviarCallback(dadosSaidaDTO);

            if (enviado) {
                final var result = dadosChecklistRepository.findById(dadosChecklist.getId());
                if (result.isPresent()) {
                    dadosChecklist = result.get();
                    dadosChecklist.setStatusCallback(true);
                    dadosChecklistService.save(dadosChecklist);
                }
            }

            envioLogFuncionalService.enviarLogFuncional();
            envioLogMonitoriaService.enviarLogMonitoria();

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                loginService.liberarUsuarioAlocado(usuario);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }
}
