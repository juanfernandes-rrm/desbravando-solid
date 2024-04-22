import cotuba.pdf.GeradorPDF;

module cotuba.pdf {
    exports cotuba.pdf;

    requires io;
    requires html2pdf;
    requires kernel;
    requires layout;
    requires cotuba.core;

    provides cotuba.plugin.GeradorEbook with GeradorPDF;
}