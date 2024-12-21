import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import static java.lang.Math.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private Double mem1 = 0D;
    private Double mem2 = 0D;
    private Double mem3 = 0D;

    private int variableID = 1;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JTextField textFieldResult;

    private ButtonGroup radioButtons0 = new ButtonGroup();
    private ButtonGroup radioButtons1 = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxVariableType = Box.createHorizontalBox();

    private int formulaId = 1;

    public Double calculate1(Double x, Double y, Double z) {
        if (x <= 0) {
            throw new IllegalArgumentException("x должно быть положительным числом, не равным нулю.");
        }

        Double root_sin = (sin(PI * y * y) < 1e-9) ? 0D : sin(PI * y * y);
        Double root_ln = (x == 1) ? 0D : log(x * x);

        Double inner = root_sin + root_ln;
        if (inner < 0) {
            throw new IllegalArgumentException("Отрицательное значение под корнем.");
        }

        return sin(sin(y) + exp(cos(y)) + z * z) * pow(inner, 0.25);
    }

    public Double calculate2(Double x, Double y, Double z) {
        if (z <= 0) {
            throw new IllegalArgumentException("z должно быть положительным числом, не равным нулю.");
        }

        Double inner = pow(y, 3) + 1;
        if (inner < 0) {
            throw new IllegalArgumentException("Отрицательное значение под корнем.");
        }

        Double denominator = pow(inner, 0.5) + log(z);
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель равен нулю.");
        }

        return pow(x, x) / denominator;
    }

    private void addRadioButton0(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons0.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButton1(String buttonName, final int variableID) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                DecimalFormat df = new DecimalFormat("0.###");
                MainFrame.this.variableID = variableID;
                switch (variableID) {
                    case 1 -> textFieldResult.setText(df.format(mem1));
                    case 2 -> textFieldResult.setText(df.format(mem2));
                    case 3 -> textFieldResult.setText(df.format(mem3));
                }
            }
        });
        radioButtons1.add(button);
        hboxVariableType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton0("Формула 1", 1);
        addRadioButton0("Формула 2", 2);

        radioButtons0.setSelected(radioButtons0.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        hboxVariableType.add(Box.createHorizontalGlue());
        addRadioButton1("Переменная 1", 1);
        addRadioButton1("Переменная 2", 2);
        addRadioButton1("Переменная 3", 3);

        radioButtons1.setSelected(radioButtons1.getElements().nextElement().getModel(), true);
        hboxVariableType.add(Box.createHorizontalGlue());

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    DecimalFormat df = new DecimalFormat("0.###");
                    if (formulaId == 1) {
                        result = calculate1(x, y, z);
                    } else {
                        result = calculate2(x, y, z);
                    }
                    textFieldResult.setText(df.format(result));

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой.", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            e.getMessage(), "Ошибочное значение числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hboxButtons0 = Box.createHorizontalBox();
        hboxButtons0.add(Box.createHorizontalGlue());
        hboxButtons0.add(buttonCalc);
        hboxButtons0.add(Box.createHorizontalStrut(30));
        hboxButtons0.add(buttonReset);
        hboxButtons0.add(Box.createHorizontalGlue());

        JButton buttonSum = new JButton("M+");
        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    DecimalFormat df = new DecimalFormat("0.###");
                    if (formulaId == 1) {
                        result = calculate1(x, y, z);
                    } else {
                        result = calculate2(x, y, z);
                    }
                    switch (variableID) {
                        case 1 -> {
                            mem1 += result;
                            textFieldResult.setText(df.format(mem1));
                        }
                        case 2 -> {
                            mem2 += result;
                            textFieldResult.setText(df.format(mem2));
                        }
                        case 3 -> {
                            mem3 += result;
                            textFieldResult.setText(df.format(mem3));
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой.", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            e.getMessage(), "Ошибочное значение числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                switch (variableID) {
                    case 1 -> {
                        mem1 = 0D;
                        textFieldResult.setText(mem1.toString());
                    }
                    case 2 -> {
                        mem2 = 0D;
                        textFieldResult.setText(mem2.toString());
                    }
                    case 3 -> {
                        mem3 = 0D;
                        textFieldResult.setText(mem3.toString());
                    }
                }
            }
        });

        Box hboxButtons1 = Box.createHorizontalBox();
        hboxButtons1.add(Box.createHorizontalGlue());
        hboxButtons1.add(buttonSum);
        hboxButtons1.add(Box.createHorizontalStrut(30));
        hboxButtons1.add(buttonMC);
        hboxButtons1.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxVariableType);
        contentBox.add(hboxButtons1);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons0);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}