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

            var opcoesCLI = new LeitorOpcoesCLI(args);
            arquivoDeSaida = opcoesCLI.getArquivoDeSaida();
            modoVerboso = opcoesCLI.isModoVerboso();

            var cotuba = new Cotuba();
            cotuba.executa(opcoesCLI);

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
