/*
Leonardo Vieira Silva - 202235038
Pablo Henrique Silva de Faria - 202235012
*/
package src;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.utils.XmlLoader;

public class TelaEditNiveis extends JPanel {
    private List<String> listaNiveis;

    public TelaEditNiveis() {
        JFrame frame = new JFrame("Registrar");

        listaNiveis = XmlLoader.loadNiveis();

        frame.setSize(400, 400);
        setSize(400, 400);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);


        JLabel titulo = new JLabel("Escolha os inimigos de cada nível!");
        titulo.setBounds(10, 50, 200, 20);
        frame.add(titulo);

        List<List<JCheckBox>> niveis = new ArrayList<List<JCheckBox>>();
        for (int j = 1; j < 5; j++) {
            JLabel label = new JLabel(j + "");
            label.setBounds(65 + j * 30, 80, 50, 20);
            frame.add(label);
        }

        for (int i = 0; i < listaNiveis.size(); i++) {
            JLabel label = new JLabel("Nível " + i + ": ");
            label.setBounds(10, 100 + i * 20, 50, 20);
            frame.add(label);

            List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
            for (int j = 1; j < 5; j++) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setBounds(60 + j * 30, 100 + i * 20, 20, 20);
                checkbox.setSelected(listaNiveis.get(i).indexOf((char) (j + '0')) != -1);
                checkboxes.add(checkbox);
                frame.add(checkbox);
            }
            niveis.add(checkboxes);
        }

        JButton salvar = new JButton("Salvar");
        salvar.setBounds(150, 300, 100, 20);
        salvar.addActionListener(e -> {
            List<String> niveisString = new ArrayList<String>();
            for (List<JCheckBox> checkboxes : niveis) {
                String nivel = "";
                for (int i = 0; i < 4; i++) {
                    if (checkboxes.get(i).isSelected()) {
                        nivel += (i + 1 + ",");
                    }
                }
                niveisString.add(nivel);
            }
            for (String nivel : niveisString) {
                System.out.println(nivel);
            }
            XmlLoader.saveNiveis(niveisString);
            new MenuInicial();
            frame.dispose();
        });
        frame.add(salvar);

        frame.add(this);
        frame.setVisible(true);
        setVisible(true);

    }
}
