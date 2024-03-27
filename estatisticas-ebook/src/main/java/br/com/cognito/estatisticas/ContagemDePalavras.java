package br.com.cognito.estatisticas;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class ContagemDePalavras implements Iterable<ContagemDePalavras.Contagem>{

    private TreeMap<String, Integer> map = new TreeMap<>();

    void adicionarPalavra(String palavra) {
        Integer contagem = map.get(palavra);

        if(contagem != null) {
            contagem++;
        }else{
            contagem = 1;
        }

        map.put(palavra, contagem);
    }

    public Iterator<Contagem> iterator() {

        Iterator<Map.Entry<String, Integer>> iterator = this.map.entrySet().iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Contagem next() {
                Map.Entry<String, Integer> entry = iterator.next();
                String palavra = entry.getKey();
                int ocorrencias = entry.getValue();
                return new Contagem(palavra, ocorrencias);
            }
        };
    }

    record Contagem(String palavra, int ocorrencias) {
    }


}
