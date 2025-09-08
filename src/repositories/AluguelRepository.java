package repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.Aluguel;
import utils.ArquivoUtil;

public class AluguelRepository {
    private List<Aluguel> aluguelList;
    private static final String ARQUIVO_ALUGUEIS = "aluguels.dat";

    public AluguelRepository(List<Aluguel> aluguelList) {
        this.aluguelList = aluguelList;
    }

        public void salvarEmArquivo() {
            ArquivoUtil.salvarLista(ARQUIVO_ALUGUEIS, aluguelList);
        }

        public void carregarDeArquivo() {
            this.aluguelList = ArquivoUtil.lerLista(ARQUIVO_ALUGUEIS);
        }

    public void salvar(Aluguel aluguel) {
        if (buscarPorId(aluguel.getId()).isEmpty()) {
            aluguelList.add(aluguel);
        } else {
            // Atualizar aluguel existente
            aluguelList.removeIf(a -> a.getId().equals(aluguel.getId()));
            aluguelList.add(aluguel);
        }
    }

    public Optional<Aluguel> buscarPorId(String id) {
        return aluguelList.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    public List<Aluguel> listarTodos() {
        return aluguelList;
    }

    public List<Aluguel> buscarAtivos() {
        return aluguelList.stream()
                .filter(Aluguel::isAtivo)
                .collect(Collectors.toList());
    }

    public List<Aluguel> buscarPorCliente(String documento) {
        return aluguelList.stream()
                .filter(a -> a.getCliente().getDocumento().equals(documento))
                .collect(Collectors.toList());
    }

    public List<Aluguel> buscarPorVeiculo(String placa) {
        return aluguelList.stream()
                .filter(a -> a.getVeiculo().getPlaca().equals(placa))
                .collect(Collectors.toList());
    }
}
