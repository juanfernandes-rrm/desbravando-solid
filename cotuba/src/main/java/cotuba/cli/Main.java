package cotuba.cli;

import cotuba.application.Cotuba;
import cotuba.cli.LeitorOpcoesCLI;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        Path diretorioDosMD;
        String formato;
        Path arquivoDeSaida;
        boolean modoVerboso = false;

        try {

            var operacoesCLI = new LeitorOpcoesCLI(args);

            diretorioDosMD = operacoesCLI.getDiretorioDosMD();
            formato = operacoesCLI.getFormato();
            arquivoDeSaida = operacoesCLI.getArquivoDeSaida();
            modoVerboso = operacoesCLI.isModoVerboso();

            var cotuba = new Cotuba();
            cotuba.executa(formato, diretorioDosMD, arquivoDeSaida);

            System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            if (modoVerboso) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
    }

}
