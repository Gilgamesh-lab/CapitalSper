package org.apache.maven.archetypes.CapitalSpéLoupGarous;
import java.util.ArrayList;
import java.util.List;

public class t {
    public static void main(String[] args) {
        String[] joueurs = { "Loup-Garou", "Villageois", "irjg", "fofjk"};

        List<List<String>> combinations = generateCombinations(joueurs);

        for (List<String> combination : combinations) {
            System.out.println("Combinaison de joueurs: " + combination);
            System.out.println("Règles du jeu :");

            if (combination.contains("Loup-Garou")) {
                System.out.println("Les Loups-Garous sont présents.");
            }
            if (combination.contains("Voyant")) {
                System.out.println("Le Voyant est présent.");
            }
            if (combination.contains("Villageois")) {
                System.out.println("Les Villageois sont présents.");
            }

            // Ajoutez ici la logique pour simuler le déroulement de la partie avec ces personnages spéciaux.

            System.out.println("==============================");
        }
    }

    private static List<List<String>> generateCombinations(String[] elements) {
        List<List<String>> combinations = new ArrayList<>();

        for (int i = 1; i <= elements.length; i++) {
            combinations.addAll(combine(elements, i));
        }

        return combinations;
    }

    private static List<List<String>> combine(String[] arr, int len) {
        List<List<String>> result = new ArrayList<>();
        combine(arr, len, 0, new ArrayList<>(), result);
        return result;
    }

    private static void combine(String[] arr, int len, int start, List<String> current, List<List<String>> result) {
        if (len == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            combine(arr, len - 1, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
