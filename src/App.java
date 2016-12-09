
/*Создать визуальное отображение ваших устройств в виде списка. 
 * Предусмотреть кнопки добавления нового компьютера и  редактирования существующего. 
 * При редактировании другого устройства выдавать сообщение об имени и мощности устройства. 
 * Проверять изменяемые параметры RAM для компьютеров и выдавать соответствующие предупреждения.*/

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

	/**
	 * Launch the application.
	 */
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

		textFieldName = new JTextField("Имя устройства");
		textFieldName.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldName);
		textFieldName.setColumns(10);

		lblPower = new JLabel("Power, W");
		panel_1.add(lblPower);

		textFieldPower = new JTextField("Мощность");
		textFieldPower.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldPower);
		textFieldPower.setColumns(10);

		lblRam = new JLabel("RAM, GB");
		panel_1.add(lblRam);

		textFieldRAM = new JTextField("Оперативная память");
		textFieldRAM.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_1.add(textFieldRAM);
		textFieldRAM.setColumns(10);

		lblHdd = new JLabel("HDD, TB");
		panel_1.add(lblHdd);

		textFieldHDD = new JTextField("Жесткий диск");
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

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedIndex() >= 0) {
					btnADD.setEnabled(true);
					btnDEL.setEnabled(true);

					for (HomeApp hp : applist) {
						if (list.getSelectedValue().toString().equals(hp.getName()))
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
		});

		btnADD = new JButton("add");
		panel_2.add(btnADD);
		btnADD.setFocusable(false);
		btnADD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Computer appH = new Computer();
				appH.setName("Новый Компьютер");
				appH.setPower(250);

				listModel.addElement(appH.getName());

				int index = list.getSelectedIndex();

				list.setSelectedIndex(applist.size());
				list.ensureIndexIsVisible(index);
				
//не работает при добавлении нового компа - не уст его поля
				
				for (HomeApp hp : applist) {
					if (list.getSelectedValue().toString().equals(hp.getName())) {
						if (hp instanceof Computer){
							textFieldName.setEnabled(true);
							textFieldPower.setEnabled(true);
							textFieldHDD.setEnabled(true);
							textFieldRAM.setEnabled(true);
							JOptionPane.showMessageDialog(contentPane,
									"Установите свои значения полей или оставте по-умолчанию");
							
						}
							
					}
				}
				
				
			}
		});

		btnDEL = new JButton("delete");
		panel_2.add(btnDEL);
		btnDEL.setFocusable(false);

		btnDEL.addActionListener(new ActionListener() {
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

		btnEDIT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//установить изменения только 1 поля+
				btnADD.setEnabled(false);
				btnDEL.setEnabled(false);
				textFieldName.setEnabled(true);
				textFieldPower.setEnabled(true);
				textFieldHDD.setEnabled(true);
				textFieldRAM.setEnabled(true);

			}
		});

		btnSAVE = new JButton("save");
		panel_2.add(btnSAVE);
		btnSAVE.setFocusable(false);
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
	private int index = 0;

	public Computer(String name, String power, String ram, String hdd) throws Exception {
		super(name, power);

		if (this.validate(power)) {
			this.power = Integer.parseInt(power);
		} else {
			throw new Exception("Неверный формат  поля Power");
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

	public int getIndex() {
		return index;
	}

}

class Mouse extends HomeApp {

	private int index = 1;

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

	public int getIndex() {
		return index;
	}
}

class Monitor extends HomeApp {

	private int index = 2;

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

	public int getIndex() {
		return index;
	}

}

class Keyboard extends HomeApp {

	private int index = 3;

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

	public int getIndex() {
		return index;
	}

}