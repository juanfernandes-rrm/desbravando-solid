package br.com.cognito.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

    @Override
    public void aposGeracao(Ebook ebook) {
        var contagemDePalavras = new ContagemDePalavras();
        for (Capitulo capitulo : ebook.capitulos()) {
            String html = capitulo.conteudoHTML();
            Document document = Jsoup.parse(html);
            String textoDoCapitulo = document.body().text();
            String textoDoCapituloSemPontuacao = textoDoCapitulo.replaceAll("\\p{Punct}", "");
            String textoDoCapituloSemAcentos = Normalizer.normalize(textoDoCapituloSemPontuacao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            String[] palavras = textoDoCapituloSemAcentos.split("\\s+");

            for (String palavra : palavras) {
                String emMaiusculas = palavra.toUpperCase();
                contagemDePalavras.adicionarPalavra(emMaiusculas);
            }

            for(ContagemDePalavras.Contagem contagem : contagemDePalavras) {
                System.out.println(contagem.palavra() + " - " + contagem.ocorrencias());
            }

        }
    }
}
