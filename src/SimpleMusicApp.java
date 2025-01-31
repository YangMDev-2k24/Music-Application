import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimpleMusicApp extends JFrame {
    private ArrayList<String> catalogue = new ArrayList<>();

    public static void main(String[] args) {
        SimpleMusicApp app = new SimpleMusicApp();
        app.setVisible(true);
    }

    public SimpleMusicApp() {
        setupWindow();
        setupComponents();
    }

    private void setupWindow() {
        setTitle("Music Catalog");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void setupComponents() {
        // Title
        JLabel title = new JLabel("My Music Collection");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Category list
        String[] genres = {"Pop", "Rock", "Jazz"," Classical", "Hip Hop", "R&B", "Country", "Reggae", "Blues", "Metal"};
        JList<String> genreList = new JList<>(genres);
        add(new JScrollPane(genreList), BorderLayout.WEST);

        // Input form
        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField artistInput = new JTextField();
        JTextField studioInput = new JTextField();
        JComboBox<String> genreSelect = new JComboBox<>(genres);
        JCheckBox availableCheck = new JCheckBox();

        form.add(new JLabel("Artist:"));
        form.add(artistInput);
        form.add(new JLabel("Studio:"));
        form.add(studioInput);
        form.add(new JLabel("Genre:"));
        form.add(genreSelect);
        form.add(new JLabel("Available:"));
        form.add(availableCheck);

        add(form, BorderLayout.CENTER);

        // Buttons
        JPanel buttons = new JPanel();
        JButton submit = new JButton("Save");
        JButton view = new JButton("View Catalogue");
        JButton exit = new JButton("Close");

        submit.addActionListener(e -> {
            String entry = String.format(
                    "Artist: %s | Studio: %s | Genre: %s | Available: %s",
                    artistInput.getText(),
                    studioInput.getText(),
                    genreSelect.getSelectedItem(),
                    availableCheck.isSelected() ? "Yes" : "No"
            );
            catalogue.add(entry);
            System.out.println("Saved: " + entry);
        });

        view.addActionListener(e -> showCatalogue());
        exit.addActionListener(e -> System.exit(0));

        buttons.add(submit);
        buttons.add(view);
        buttons.add(exit);
        add(buttons, BorderLayout.SOUTH);
    }

    private void showCatalogue() {
        if (catalogue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Catalogue is empty!");
            return;
        }

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        for (String entry : catalogue) {
            textArea.append(entry + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Music Catalogue", JOptionPane.PLAIN_MESSAGE);
    }
}