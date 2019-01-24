package challenge;
import challenge.Jogador;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
/**
 * @author elvisbaranoski
 */
public class Main {

	public List<Jogador> jogadores = new ArrayList<>();

	public Main() {
		File  archieveCSV = new File("src/main/resources/data.csv");
		Scanner gravar = null;
		try {
			gravar = new Scanner(archieveCSV);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gravar.nextLine();
		while(gravar.hasNext()){
			String str[] = gravar.nextLine().split(",");
			try {
				jogadores.add(new Jogador(str[2], str[3], new Integer(str[6]),	str[8],str[14], new Double(str[17]), str[18]));
			} catch (ParseException e) {
				e.printStackTrace();
			}


		}
	}



	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		return ((int) jogadores.stream()
				.map(Jogador::getNationality)
				.distinct()
				.count());


	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		return ((int) jogadores.stream()
				.filter(j -> StringUtils.isNotEmpty(j.getClub()))
				.map(Jogador::getClub)
				.distinct()
				.count());
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {

		return jogadores.subList(0, 20).stream()
				.map(Jogador::getFullName)
				.collect(Collectors.toList());
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		return jogadores.stream()
				.filter(j -> j.getEurReleaseClause() != null)
				.sorted(Comparator.comparing(Jogador::getEurReleaseClause).reversed())
				.map(Jogador::getFullName)
				.limit(10)
				.collect(Collectors.toList());

	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getBirthDate)
						.thenComparing(Jogador::getEurWage))
				.map(Jogador::getFullName)
				.limit(10)
				.collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		Map<Integer, Long> mapAgrupado = jogadores.stream()
				.collect(Collectors.groupingBy(Jogador::getAge, Collectors.counting()));

		Map<Integer, Integer> retorno = mapAgrupado.entrySet()
				.stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().intValue()));

		return retorno;
	}

}
