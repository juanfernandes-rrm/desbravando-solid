package cotuba.plugin;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.util.ServiceLoader;

public interface Plugin {

    static void renderizou(Capitulo capitulo) {
        ServiceLoader.load(Plugin.class)
                .forEach(plugin -> {
                    String html = capitulo.getConteudoHTML();
                    String htmlModificado = plugin.aposRenderizacao(html);
                    capitulo.setConteudoHTML(htmlModificado);
                });
    }

    static void gerou(Ebook ebook) {
        ServiceLoader.load(Plugin.class)
                .forEach(plugin -> {
                    plugin.aposGeracao(ebook);
                });
    }

    String aposRenderizacao(String HTML);

    void aposGeracao(Ebook ebook);

}
