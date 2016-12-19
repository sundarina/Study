
/*Создать визуальное отображение ваших устройств в виде списка. 
 * Предусмотреть кнопки добавления нового компьютера и  редактирования существующего. 
 * При редактировании другого устройства выдавать сообщение об имени и мощности устройства. 
 * Проверять изменяемые параметры RAM для компьютеров и выдавать соответствующие предупреждения.*/

//Розумняк Дарья

import java.awt.BorderLayout;
import java.io.*;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;

public class App extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldPower;
	private JTextField textFieldRAM;
	List<HomeApp> applist;
	JList list;
	DefaultListModel listModel;
	private JButton btnDEL;
	private JButton btnADD;
	private JTextField textFieldHDD;
	private JLabel lblName;
	private JLabel lblPower;
	private JLabel lblRam;
	private JLabel lblHdd;
	private JButton btnEDIT;
	private JButton btnSAVE;
	JOptionPane except;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() throws Exception {

		applist = new ArrayList<HomeApp>();
		applist.add(new Monitor("Монитор", "30"));
		applist.add(new Mouse("Мышь", "1"));
		applist.add(new Keyboard("Клавиатура", "2"));
		applist.add(new Computer("Компьютер", "400", "16", "7"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(10, 1));

		lblName = new JLabel("Name");
		panel_1.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldName);
		textFieldName.setColumns(10);

		lblPower = new JLabel("Power, W");
		panel_1.add(lblPower);

		textFieldPower = new JTextField();
		textFieldPower.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldPower);
		textFieldPower.setColumns(10);

		lblRam = new JLabel("RAM, GB");
		panel_1.add(lblRam);

		textFieldRAM = new JTextField();
		textFieldRAM.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldRAM);
		textFieldRAM.setColumns(10);

		lblHdd = new JLabel("HDD, TB");
		panel_1.add(lblHdd);

		textFieldHDD = new JTextField();
		textFieldHDD.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldHDD);
		textFieldHDD.setColumns(10);

		textFieldName.setEnabled(false);
		textFieldPower.setEnabled(false);
		textFieldHDD.setEnabled(false);
		textFieldRAM.setEnabled(false);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);

		listModel = new DefaultListModel<>();

		for (HomeApp hp : applist) {
			listModel.addElement(hp.getName());
		}

		list = new JList();
		list.setModel(listModel);
		list.setVisibleRowCount(10);
		panel.add(list, BorderLayout.CENTER);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setFocusable(false);

		list.addListSelectionListener(new ListSelectionListener() { // обработка выбора в списке

			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedIndex() >= 0) {
					btnADD.setEnabled(true);
					btnDEL.setEnabled(true);
					btnEDIT.setEnabled(true);
					btnSAVE.setEnabled(true);

					for (HomeApp hp : applist) {
						if (list.getSelectedValue().toString().equals(hp.getName())) {

							if (hp instanceof Computer) {
								textFieldName.setText(hp.getName());
								textFieldPower.setText(hp.getPower() + "");

								textFieldHDD.setText(((Computer) hp).getHdd() + "");
								textFieldRAM.setText(((Computer) hp).getRam() + "");
								textFieldHDD.setVisible(true);
								textFieldRAM.setVisible(true);
								lblHdd.setVisible(true);
								lblRam.setVisible(true);

								textFieldHDD.setEnabled(false);
								textFieldRAM.setEnabled(false);

							} else {
								textFieldName.setText(hp.getName());
								textFieldPower.setText(hp.getPower() + "");
								lblHdd.setVisible(false);
								lblRam.setVisible(false);
								textFieldHDD.setVisible(false);
								textFieldRAM.setVisible(false);
							}
						}
					}
				}

			}
		});

		btnADD = new JButton("add");
		panel_2.add(btnADD);
		btnADD.setFocusable(false);
		btnADD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Computer comp = new Computer();
				applist.add(comp);
				comp.setName("Новый Компьютер");
				listModel.addElement(comp.getName());

				textFieldName.setName(comp.getName() + "");
				comp.setPower(250);
				textFieldPower.setText(comp.getPower() + "");
				comp.setHdd(2);
				textFieldHDD.setText(comp.getHdd() + "");
				comp.setRam(8);
				textFieldRAM.setText(comp.getRam() + "");

				list.setSelectedIndex(applist.size() - 1);
				int index = list.getSelectedIndex();
				list.ensureIndexIsVisible(index);

				JOptionPane.showMessageDialog(contentPane,
						"Установите свои значения полей кнопкой Edit или оставьте по-умолчанию");

			}
		});

		btnDEL = new JButton("delete");
		panel_2.add(btnDEL);
		btnDEL.setFocusable(false);
		btnDEL.setEnabled(false);

		btnDEL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int index = list.getSelectedIndex();
				listModel.remove(index);

				int size = listModel.getSize();
				if (size == 0) {
					btnDEL.setEnabled(false);
				} else {
					if (index == listModel.getSize()) {
						index--;
					}
					list.setSelectedIndex(index);
					list.ensureIndexIsVisible(index);
				}
			}
		});

		btnEDIT = new JButton("edit");
		panel_2.add(btnEDIT);
		btnEDIT.setFocusable(false);
		btnEDIT.setEnabled(false);
		btnEDIT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnADD.setEnabled(false);
				btnDEL.setEnabled(false);
				textFieldName.setEnabled(true);
				textFieldPower.setEnabled(true);
				textFieldHDD.setEnabled(true);
				textFieldRAM.setEnabled(true);
				list.setEnabled(false);
			}
		});

		btnSAVE = new JButton("save");
		panel_2.add(btnSAVE);
		btnSAVE.setFocusable(false);
		btnSAVE.setEnabled(false);
		btnSAVE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (HomeApp hp : applist) {
					if (list.getSelectedValue().toString().equals(hp.getName())) {
						hp.setName(textFieldName.getText());
						textFieldName.setEnabled(false);
						int index = list.getSelectedIndex();
						listModel.set(index, hp.getName());

						try {
							hp.setPower(Integer.parseInt(textFieldPower.getText()));
							textFieldPower.setEnabled(false);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(contentPane, "Неверный формат числа");
						}

						if ((Integer.parseInt(textFieldPower.getText()) > 0)
								&& (Integer.parseInt(textFieldPower.getText()) <= 1000)) {

							hp.setPower(Integer.parseInt(textFieldPower.getText()));
							textFieldPower.setEnabled(false);
							list.setEnabled(true);

						} else {
							JOptionPane.showMessageDialog(contentPane,
									"Неверный формат числа. Возможный диапазон Power: от 1 до 1000");
							textFieldPower.setEnabled(true);
							list.setEnabled(false);

						}

						if (hp instanceof Computer) {

							hp.setName(textFieldName.getText());
							textFieldName.setEnabled(false);
							index = list.getSelectedIndex();
							listModel.set(index, hp.getName());

							try {
								hp.setPower(Integer.parseInt(textFieldPower.getText()));
								textFieldPower.setEnabled(false);
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(contentPane, "Неверный формат числа");
							}

							try {
								((Computer) hp).setRam(Integer.parseInt(textFieldRAM.getText()));
								textFieldRAM.setEnabled(false);
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(contentPane, "Неверный формат числа");
							}
							try {
								((Computer) hp).setHdd(Integer.parseInt(textFieldHDD.getText()));
								textFieldHDD.setEnabled(false);
							} catch (Exception ex) {
								JOptionPane.showMessageDialog(contentPane, "Неверный формат числа");
							}

							if ((Integer.parseInt(textFieldRAM.getText()) == 4)
									|| (Integer.parseInt(textFieldRAM.getText()) == 8)
									|| (Integer.parseInt(textFieldRAM.getText()) == 16)
									|| (Integer.parseInt(textFieldRAM.getText()) == 32)) {

								((Computer) hp).setRam(Integer.parseInt(textFieldRAM.getText()));
								textFieldRAM.setEnabled(false);
								list.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(contentPane,
										"Неверный формат числа. Возможный диапазон RAM: от 4 до 32");
								textFieldRAM.setEnabled(true);
								list.setEnabled(false);
							}

							if (Integer.parseInt(textFieldHDD.getText()) > 0
									&& Integer.parseInt(textFieldHDD.getText()) < 8) {
								((Computer) hp).setHdd(Integer.parseInt(textFieldHDD.getText()));
								textFieldHDD.setEnabled(false);
								list.setEnabled(true);
							} else {
								JOptionPane.showMessageDialog(contentPane,
										"Неверный формат числа. Возможныйдиапазон HDD: от 1 до 8 терабайт");
								textFieldHDD.setEnabled(true);
								list.setEnabled(false);
							}

							if ((Integer.parseInt(textFieldPower.getText()) > 0)
									&& (Integer.parseInt(textFieldPower.getText()) <= 1000)) {

								((Computer) hp).setPower(Integer.parseInt(textFieldPower.getText()));
								textFieldPower.setEnabled(false);
								list.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(contentPane,
										"Неверный формат числа. Возможный диапазон Power: от 1 до 1000");
								textFieldPower.setEnabled(true);
								list.setEnabled(false);
							}

						}
					}

					btnADD.setEnabled(true);
					btnDEL.setEnabled(true);
				}
			}
		});

	}
}

class HomeApp {

	private String name;
	protected int power;

	public HomeApp(String name, String power) throws Exception {
		this.name = name;
		if (this.validate(power)) {
			this.power = Integer.parseInt(power);
		} else {
			throw new Exception("Неверный формат поля Power");
		}

	}

	public HomeApp() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	protected boolean validate(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}

class Computer extends HomeApp {
	private int ram;
	private int hdd;

	public Computer(String name, String power, String ram, String hdd) throws Exception {
		super(name, power);

		if (this.validate(power)) {
			this.power = Integer.parseInt(power);
		} else {
			throw new Exception("Неверный формат поля Power");
		}
		if (this.validate(ram)) {
			this.ram = Integer.parseInt(ram);
		} else {
			throw new Exception("Неверный формат поля RAM");
		}
		if (this.validate(hdd)) {
			this.hdd = Integer.parseInt(hdd);
		} else {
			throw new Exception("Неверный формат поля HDD");
		}

	}

	public Computer() {
		super();

	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {

		this.ram = ram;

	}

	public int getHdd() {
		return hdd;
	}

	public void setHdd(int hdd) {

		this.hdd = hdd;
	}
}

class Mouse extends HomeApp {

	public Mouse(String name, String power) throws Exception {
		super(name, power);
		if (this.validate(power)) {
			this.power = Integer.parseInt(power);
		} else {
			throw new Exception("Неверный формат поля Power");
		}
	}

	public Mouse() {
		super();

	}
}

class Monitor extends HomeApp {

	public Monitor(String name, String power) throws Exception {
		super(name, power);
		if (this.validate(power)) {
			this.power = Integer.parseInt(power);
		} else {
			throw new Exception("Неверный формат поля Power");
		}
	}

	public Monitor() {
		super();

	}
}

class Keyboard extends HomeApp {

	public Keyboard(String name, String power) throws Exception {
		super(name, power);
		if (this.validate(power)) {
			this.power = Integer.parseInt(power);
		} else {
			throw new Exception("Неверный формат  поля Power");
		}
	}

	public Keyboard() {
		super();

	}
}