import cotuba.epub.GeradorEPUB;

module cotuba.epub {
    exports cotuba.epub;

    requires epublib.core;
    requires cotuba.core;

    provides cotuba.plugin.GeradorEbook with GeradorEPUB;
}