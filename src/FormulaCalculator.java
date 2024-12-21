//
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.text.DecimalFormat;
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JRadioButton;
//import javax.swing.JTextField;
//import javax.swing.plaf.synth.SynthOptionPaneUI;
//
//import static java.lang.Math.*;
//
//
//public class FormulaCalculator extends JFrame {
//
//    private static final int WIDTH = 500;
//    private static final int HEIGHT = 500;
//
//    private Double mem1 = 0D;
//    private Double mem2 = 0D;
//    private Double mem3 = 0D;
//
//    private int variableID = 1;
//
//    private JTextField textFieldX;
//    private JTextField textFieldY;
//    private JTextField textFieldZ;
//
//    private JTextField textFieldResult;
//
//    private ButtonGroup radioButtons0 = new ButtonGroup();
//    private ButtonGroup radioButtons1 = new ButtonGroup();
//    private Box hboxFormulaType = Box.createHorizontalBox();
//    private Box hboxVariableType = Box.createHorizontalBox();
//
//    private int formulaId = 1;
//
//    public Double calculate1(Double x, Double y, Double z) {
//        if (x <= 0) {
//            throw new IllegalArgumentException("x положительное число не равное нулю.");
//        }
//        Double root_sin;
//        if(sin(PI*y*y)<1e-9){
//            root_sin = 0D; }
//        else{
//            root_sin = sin(PI*y*y);}
//        Double root_ln;
//        if(x == 1){
//            root_ln = 0D; }
//        else{
//            root_ln = log(x*x);}
//        Double inner = root_sin+root_ln;
//        if (inner < 0) {
//            throw new IllegalArgumentException("Отрицательное значение под корнем");
//        }
//
//        return sin(sin(y)+exp(cos(y))+z*z)*pow(inner, 0.25);
//    }
//    public Double calculate2(Double x, Double y, Double z) {
//        if (z <= 0) {
//            throw new IllegalArgumentException("z положительное число не равное нулю.");
//        }
//        Double inner = pow(y,3)+1;
//
//        if (inner < 0) {
//            throw new IllegalArgumentException("Отрицательное значение под корнем");
//        }
//
//        Double denominator = pow(inner,0.5)+log(z);
//        if (denominator == 0) {
//            throw new IllegalArgumentException("Знаменатель ноль");
//        }
//
//        return pow(x, x)/denominator;
//    }
//
//    private void addRadioButton0(String buttonName, final int formulaId) {
//        JRadioButton button = new JRadioButton(buttonName);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                MainFrame.this.formulaId = formulaId;
//            }
//        });
//        radioButtons0.add(button);
//        hboxFormulaType.add(button);
//    }
//    private void addRadioButton1(String buttonName, final int variableID) {
//        JRadioButton button = new JRadioButton(buttonName);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                DecimalFormat df = new DecimalFormat("0.###");
//                MainFrame.this.variableID = variableID;
//                switch (variableID){
//                    case(1):
//                        textFieldResult.setText(df.format(mem1).toString());
//                        break;
//                    case(2):
//                        textFieldResult.setText(df.format(mem2).toString());
//                        break;
//                    case(3):
//                        textFieldResult.setText(df.format(mem3).toString());
//                        break;
//                }
//            }
//        });
//        radioButtons1.add(button);
//        hboxVariableType.add(button);
//    }
//
//
//    public MainFrame(){
//        super("Вычисление формулы");
//
//        setSize(WIDTH, HEIGHT);
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        // Центрировать окно приложения на экране
//        setLocation((kit.getScreenSize().width - WIDTH)/2,
//                (kit.getScreenSize().height - HEIGHT)/2);
//
//        hboxFormulaType.add(Box.createHorizontalGlue());
//        addRadioButton0("Формула 1", 1);
//        addRadioButton0("Формула 2", 2);
//
//        radioButtons0.setSelected(radioButtons0.getElements().nextElement().getModel(), true);
//        hboxFormulaType.add(Box.createHorizontalGlue());
////        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
//
//        hboxVariableType.add(Box.createHorizontalGlue());
//        addRadioButton1("Переменная 1", 1);
//        addRadioButton1("Переменная 2", 2);
//        addRadioButton1("Переменная 3", 3);
//
//        radioButtons1.setSelected(radioButtons1.getElements().nextElement().getModel(), true);
//        hboxVariableType.add(Box.createHorizontalGlue());
////        hboxVariableType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
//
//        // Создать область с полями ввода для X и Y, Z
//        JLabel labelForX = new JLabel("X:");
//        textFieldX = new JTextField("0", 10);
//        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
//        JLabel labelForY = new JLabel("Y:");
//        textFieldY = new JTextField("0", 10);
//        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
//        JLabel labelForZ = new JLabel("Z:");
//        textFieldZ = new JTextField("0", 10);
//        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
//        Box hboxVariables = Box.createHorizontalBox();
////        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
////        hboxVariables.add(Box.createHorizontalGlue());
//        hboxVariables.add(labelForX);
//        hboxVariables.add(Box.createHorizontalStrut(10));
//        hboxVariables.add(textFieldX);
////        hboxVariables.add(Box.createHorizontalStrut(10));
//        hboxVariables.add(Box.createHorizontalGlue());
//        hboxVariables.add(labelForY);
//        hboxVariables.add(Box.createHorizontalStrut(10));
//        hboxVariables.add(textFieldY);
////        hboxVariables.add(Box.createHorizontalStrut(10));
//        hboxVariables.add(Box.createHorizontalGlue());
//        hboxVariables.add(labelForZ);
//        hboxVariables.add(Box.createHorizontalStrut(10));
//        hboxVariables.add(textFieldZ);
////        hboxVariables.add(Box.createHorizontalGlue());
//
//// Создать область для вывода результата
//        JLabel labelForResult = new JLabel("Результат:");
//        textFieldResult = new JTextField("0", 10);
//        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
//        Box hboxResult = Box.createHorizontalBox();
//        hboxResult.add(Box.createHorizontalGlue());
//        hboxResult.add(labelForResult);
//        hboxResult.add(Box.createHorizontalStrut(10));
//        hboxResult.add(textFieldResult);
//        hboxResult.add(Box.createHorizontalGlue());
////        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
//
//// Создать область для кнопок
//        JButton buttonCalc = new JButton("Вычислить");
//        buttonCalc.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                try {
//                    Double x = Double.parseDouble(textFieldX.getText());
//                    Double y = Double.parseDouble(textFieldY.getText());
//                    Double z = Double.parseDouble(textFieldZ.getText());
//                    Double result;
//                    DecimalFormat df = new DecimalFormat("0.###");
//                    if (formulaId == 1){
//                        result = calculate1(x, y, z);}
//                    else{
//                        result = calculate2(x, y, z);}
//                    textFieldResult.setText(df.format(result).toString());
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(MainFrame.this,
//                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
//                            JOptionPane.WARNING_MESSAGE);
//                } catch (IllegalArgumentException e) {
//                    JOptionPane.showMessageDialog(MainFrame.this,
//                            "Введено некорректное значение для переменных", "Ошибочное значения числа",
//                            JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
//        JButton buttonReset = new JButton("Очистить поля");
//        buttonReset.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                textFieldX.setText("0");
//                textFieldY.setText("0");
//                textFieldZ.setText("0");
//                textFieldResult.setText("0");
//
//            }
//        });
//        Box hboxButtons0 = Box.createHorizontalBox();
//        hboxButtons0.add(Box.createHorizontalGlue());
//        hboxButtons0.add(buttonCalc);
//        hboxButtons0.add(Box.createHorizontalStrut(30));
//        hboxButtons0.add(buttonReset);
//        hboxButtons0.add(Box.createHorizontalGlue());
////        hboxButtons0.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//
//        // Создать область для кнопок MC, M+
//        JButton buttonSum = new JButton("M+");
//        buttonSum.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                try { Double x = Double.parseDouble(textFieldX.getText());
//                    Double y = Double.parseDouble(textFieldY.getText());
//                    Double z = Double.parseDouble(textFieldZ.getText());
//                    Double result;
//                    DecimalFormat df = new DecimalFormat("0.###");
//                    if (formulaId==1)
//                        result = calculate1(x, y, z);
//                    else
//                        result = calculate2(x, y, z);
//                    switch (variableID){
//                        case 1: mem1 += result;
//                            textFieldResult.setText(df.format(mem1).toString());
//                            break;
//                        case 2: mem2 += result;
//                            textFieldResult.setText(df.format(mem2).toString());
//                            break;
//                        case 3: mem3 += result;
//                            textFieldResult.setText(df.format(mem3).toString());
//                            break;
////                        default: System.out.println("???");
//                    }
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(MainFrame.this,
//                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
//                            JOptionPane.WARNING_MESSAGE);
//                }
//                catch (IllegalArgumentException e) {
//                    JOptionPane.showMessageDialog(MainFrame.this,
//                            "Введено некорректное значение для переменных", "Ошибочное значения числа",
//                            JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
//        JButton buttonMC = new JButton("MC");
//        buttonMC.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ev) {
//                switch (variableID) {
//                    case 1:
//                        mem1 = 0D;
//                        textFieldResult.setText(mem1.toString());
//                        break;
//                    case 2:
//                        mem2 = 0D;
//                        textFieldResult.setText(mem2.toString());
//                        break;
//                    case 3:
//                        mem3 = 0D;
//                        textFieldResult.setText(mem3.toString());
//                        break;
////                    default:
////                        System.out.println("???");
//                }
//
//            }
//        });
//        Box hboxButtons1 = Box.createHorizontalBox();
//        hboxButtons1.add(Box.createHorizontalGlue());
//        hboxButtons1.add(buttonSum);
//        hboxButtons1.add(Box.createHorizontalStrut(30));
//        hboxButtons1.add(buttonMC);
//        hboxButtons1.add(Box.createHorizontalGlue());
////        hboxButtons1.setBorder(BorderFactory.createLineBorder(Color.GREEN));
//
//        // Связать области воедино в компоновке BoxLayout
//        Box contentBox = Box.createVerticalBox();
//        contentBox.add(Box.createVerticalGlue());
//        contentBox.add(hboxFormulaType);
//        contentBox.add(hboxVariables);
//        contentBox.add(hboxVariableType);
//        contentBox.add(hboxButtons1);
//        contentBox.add(hboxResult);
//        contentBox.add(hboxButtons0);
//        contentBox.add(Box.createVerticalGlue());
//        getContentPane().add(contentBox, BorderLayout.CENTER);
//
//    }
//
//
//}
