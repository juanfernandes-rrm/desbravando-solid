package br.com.cognito.estatisticas;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class ContagemDePalavras {

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

    Set<Map.Entry<String, Integer>> entrySet() {
        return map.entrySet();
    }

}
