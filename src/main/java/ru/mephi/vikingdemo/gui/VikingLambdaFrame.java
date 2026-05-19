package ru.mephi.vikingdemo.gui;

import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingLambdaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VikingLambdaFrame extends JFrame {

    private final VikingLambdaService service;
    private final JTextArea textArea = new JTextArea();

    public VikingLambdaFrame(VikingLambdaService service) {

        this.service = service;

        setTitle("Lambda operations");
        setSize(700, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JButton button = new JButton("Show info");

        button.addActionListener(e -> loadData());

        add(button, BorderLayout.NORTH);

        textArea.setEditable(false);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    private void loadData() {

        StringBuilder sb = new StringBuilder();

        sb.append("Older than 30: ")
                .append(service.countOlderThan(30))
                .append("\n");

        sb.append("Younger than 25: ")
                .append(service.countYoungerThan(25))
                .append("\n");

        sb.append("Age between 20 and 40: ")
                .append(service.countAgeBetween(20, 40))
                .append("\n");

        sb.append("Age outside 20 and 40: ")
                .append(service.countAgeOutside(20, 40))
                .append("\n");

        sb.append("Red hair + LONG beard: ")
                .append(service.countByBeardAndHair(
                        ru.mephi.vikingdemo.model.BeardStyle.LONG,
                        ru.mephi.vikingdemo.model.HairColor.Red
                ))
                .append("\n");

        sb.append("One axe: ")
                .append(service.countOneAxe())
                .append("\n");

        sb.append("Two axes: ")
                .append(service.countTwoAxes())
                .append("\n\n");
        sb.append("Vikings with one or two axes: ")
                .append(service.countOneOrTwoAxes())
                .append("\n\n");


        Viking tall = service.randomTallViking();

        sb.append("Random tall viking (>180):\n");

        if (tall != null) {

            sb.append(tall.name())
                    .append(", age ")
                    .append(tall.age())
                    .append(", height ")
                    .append(tall.heightCm())
                    .append("\n\n");
        }


        sb.append("Max ID: ")
                .append(service.maxId())
                .append("\n\n");

        sb.append("Even IDs:\n");

        service.evenIds()
                .forEach(id ->
                        sb.append(id).append("\n")
                );


        sb.append("\nLegendary vikings:\n");

        service.legendaryVikings()
                .forEach(v ->
                        sb.append(v.name())
                                .append(" ")
                                .append(v.age())
                                .append("\n")
                );

        sb.append("\nRed-haired vikings sorted by age:\n");

        service.redBeardSorted()
                .forEach(v ->
                        sb.append(v.name())
                                .append(" age ")
                                .append(v.age())
                                .append("\n")
                );

        textArea.setText(sb.toString());
    }
}
