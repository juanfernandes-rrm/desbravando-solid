module cotuba.core {
    requires org.commonmark;

    exports cotuba.application;
    exports cotuba.domain;
    exports cotuba.plugin;

    uses cotuba.plugin.AoRenderizarHTML;
    uses cotuba.plugin.AoFinalizarGeracao;
    uses cotuba.plugin.GeradorEbook;
}