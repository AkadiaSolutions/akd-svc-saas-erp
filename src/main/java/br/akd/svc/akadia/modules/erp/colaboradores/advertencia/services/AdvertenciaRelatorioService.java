package br.akd.svc.akadia.modules.erp.colaboradores.advertencia.services;

import br.akd.svc.akadia.modules.erp.colaboradores.advertencia.models.entity.AdvertenciaEntity;
import br.akd.svc.akadia.modules.erp.colaboradores.colaborador.models.entity.ColaboradorEntity;
import br.akd.svc.akadia.utils.ConversorDeDados;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InvalidClassException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class AdvertenciaRelatorioService {

    public void exportarPdf(HttpServletResponse response,
                            ColaboradorEntity usuarioLogado,
                            ColaboradorEntity colaboradorAdvertencia,
                            AdvertenciaEntity advertenciaEntity)
            throws DocumentException, IOException {

        log.debug("Método responsável por encaminhar para métodos de construção de PDF de relatório de colaboradores e " +
                "retornar PDF construído acessado");

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=akadion_"
                + usuarioLogado.getEmpresa().getNome().replace(" ", "-").toLowerCase()
                + "_colaboradores_"
                + new SimpleDateFormat("dd.MM.yyyy_HHmmss").format(new Date())
                + ".pdf";
        response.setHeader(headerKey, headerValue);

        log.debug("Iniciando construção do objeto Document, que se trata da estrutura do PDF gerado...");
        try (Document document = new Document(PageSize.A4, 60, 60, 40, 40)) {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            log.debug("Iniciando acesso ao método de construção do título do PDF...");
            document.add(constroiParagrafoTitulo());
            constroiCabecalho(document, colaboradorAdvertencia, advertenciaEntity);
            log.info("PDF gerado com sucesso");
        } catch (Exception e) {
            log.error("Ocorreu um erro na criação do PDF: {}", e.getMessage());
            throw new InvalidClassException(e.toString());
        }

    }

    public Paragraph constroiParagrafoTitulo() {
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 15, Color.BLACK);
        Paragraph p = new Paragraph("ADVERTÊNCIA DISCIPLINAR", font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(0);
        p.setSpacingAfter(20);
        return p;
    }

    public void constroiCabecalho(Document document, ColaboradorEntity colaboradorAdvertencia, AdvertenciaEntity advertencia) {
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.COURIER, 12, Color.BLACK);
        com.lowagie.text.Font fontBold = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Color.BLACK);

        Paragraph p;

        p = new Paragraph("EMPREGADOR: " + colaboradorAdvertencia.getEmpresa().getRazaoSocial().toUpperCase(), fontBold);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(0);
        document.add(p);

        p = new Paragraph("EMPREGADO:  " + colaboradorAdvertencia.getNome().toUpperCase(), fontBold);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(5);
        document.add(p);

        p = new Paragraph("Este documento tem como finalidade de aplicar a pena de advertência disciplinar" +
                " ao colaborador de nome " + colaboradorAdvertencia.getNome() + " pelo ocorrido na data do dia " +
                ConversorDeDados.converteDataUsParaDataBr(advertencia.getDataCadastro()), font);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(20);
        document.add(p);

        p = new Paragraph(advertencia.getMotivo().toUpperCase(), fontBold);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(5);
        document.add(p);

        p = new Paragraph(advertencia.getDescricao(), font);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(20);
        document.add(p);

        p = new Paragraph("Esclarecemos que a repetição de procedimentos como este poderá ser considerado ato " +
                "faltoso, possível de dispensa por justa causa. Para que não tenhamos, no futuro, de tomar as medidas " +
                "que nos facultam a legislação vigente, solicitamos que observe as normas reguladoras da relação de emprego.", font);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(20);
        document.add(p);

        p = new Paragraph("Favor dar seu ciente na cópia desta", font);
        p.setAlignment(Element.ALIGN_LEFT);
        p.setSpacingBefore(0);
        p.setSpacingAfter(30);
        document.add(p);

        p = new Paragraph("_________________________, ______ de _______________ de _____", font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(0);
        p.setSpacingAfter(30);
        document.add(p);

        p = new Paragraph("______________________________________________________________", font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(0);
        p.setSpacingAfter(5);
        document.add(p);

        p = new Paragraph("Empregado", fontBold);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(0);
        p.setSpacingAfter(30);
        document.add(p);

        p = new Paragraph("______________________________________________________________", font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(0);
        p.setSpacingAfter(5);
        document.add(p);

        p = new Paragraph("Empregador", fontBold);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(0);
        p.setSpacingAfter(30);
        document.add(p);

    }

}
