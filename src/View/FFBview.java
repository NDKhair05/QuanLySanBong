package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import DataBase.JDBCUtil;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FFBview extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_ThongTin;
	private JTextField textField_ChonGioThue;
	private JTextField textField_ChonGioTra;
	private JTable table_ChonSan;
	private JTabbedPane tabbedPane;
	private JTextField textField_chonNgay;
	private JComboBox<String> comboBox_LoaiSan;
	private JComboBox<String> comboBox_ChonSan;
	private JTextField textField_cccd;
	private JTextField textField_hovaten;
	private JTextField textField_sdt;
	private DefaultTableModel bangThongTin;
	private DefaultTableModel bangChonSan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {try {
        // Thiết lập Look and Feel cụ thể (ví dụ: Nimbus)
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        e.printStackTrace();
    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FFBview frame = new FFBview();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FFBview() {
		URL urllogo = FFBview.class.getResource("logo1.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urllogo);
		this.setIconImage(img);
		
		setTitle("CHƯƠNG TRÌNH QUẢN LÍ CHO THUÊ SÂN BÓNG MINI");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(100,100);
		setBounds(100, 100, 1204, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDatSan = new JButton("Đặt Sân ");
		btnDatSan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tabbedPane.setSelectedIndex(3);
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 64, 128));
		panel_2.setBounds(11, 11, 950, 28);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trang chủ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 0, 164, 28);
		panel_2.add(lblNewLabel);
		btnDatSan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnDatSan.setBounds(971, 369, 215, 55);
		contentPane.add(btnDatSan);
		
		JButton btnHuySan = new JButton("Hủy Sân ");
		btnHuySan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huySan();
			}
		});
		btnHuySan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnHuySan.setBounds(971, 500, 215, 55);
		contentPane.add(btnHuySan);
		
		JButton btnXemThongTin = new JButton("Xem Thông Tin ");
		btnXemThongTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				xemThongTin();
			}
		});
		btnXemThongTin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnXemThongTin.setBounds(971, 566, 215, 55);
		contentPane.add(btnXemThongTin);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(11, 11, 950, 610);
		contentPane.add(tabbedPane);
		
		
		JPanel panel_ThongTin = new JPanel();
		tabbedPane.addTab(null, null, panel_ThongTin, null);
		panel_ThongTin.setLayout(null);
		
		table_ThongTin = new JTable();
		table_ThongTin.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
			}
		));
		table_ThongTin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bangThongTin = (DefaultTableModel) table_ThongTin.getModel();
		table_ThongTin.setRowHeight(30);
	    bangThongTin.addColumn("Mã đặt sân");
	    bangThongTin.addColumn("Họ và Tên");
	    bangThongTin.addColumn("Số điện thoại");
	    bangThongTin.addColumn("Sân");
	    bangThongTin.addColumn("Ngày ");
	    bangThongTin.addColumn("Thời gian thuê");
	    bangThongTin.addColumn("Thời gian trả");
	    bangThongTin.addColumn("Thanh Toán");
		
		JScrollPane scrollPane_ThongTin = new JScrollPane(table_ThongTin);
		scrollPane_ThongTin.setBounds(10, 11, 925, 560);
		panel_ThongTin.add(scrollPane_ThongTin);
		
		JPanel panel_ChonSan = new JPanel();
		tabbedPane.addTab(null, null, panel_ChonSan, null);
		panel_ChonSan.setLayout(null);
		

		
	    comboBox_ChonSan = new JComboBox<String>();
		comboBox_ChonSan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_ChonSan.setBounds(10, 47, 145, 37);
		panel_ChonSan.add(comboBox_ChonSan);
		
		JButton btnChonGioThue = new JButton("Chọn giờ thuê");
		btnChonGioThue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                // Tạo JComboBox cho giờ
                JComboBox<Integer> hourComboBox = new JComboBox<>();
                for (int i = 0; i < 24; i++) {
                    hourComboBox.addItem(i);
                }

                // Tạo JComboBox cho phút
                JComboBox<Integer> minuteComboBox = new JComboBox<>();
                for (int i = 0; i < 60; i++) {
                    minuteComboBox.addItem(i);
                }

                // Tạo JPanel để chứa các thành phần chọn giờ
                JPanel timePanel = new JPanel();
                timePanel.add(hourComboBox);
                timePanel.add(new JLabel(":"));
                timePanel.add(minuteComboBox);

                // Hiển thị hộp thoại chọn giờ
                int result = JOptionPane.showConfirmDialog(contentPane, timePanel, "Chọn giờ", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    int hour = (int) hourComboBox.getSelectedItem();
                    int minute = (int) minuteComboBox.getSelectedItem();

                    // Định dạng giờ thành chuỗi và hiển thị trong JTextField
                    String timeString = String.format("%02d:%02d:00", hour, minute);
                    textField_ChonGioThue.setText(timeString);
                }
            
			}
		});
		btnChonGioThue.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnChonGioThue.setBounds(193, 11, 190, 37);
		panel_ChonSan.add(btnChonGioThue);
		
		JButton btnChonGioTra = new JButton("Chọn giờ trả");
		btnChonGioTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


                // Tạo JComboBox cho giờ
                JComboBox<Integer> hourComboBox = new JComboBox<>();
                for (int i = 0; i < 24; i++) {
                    hourComboBox.addItem(i);
                }

                // Tạo JComboBox cho phút
                JComboBox<Integer> minuteComboBox = new JComboBox<>();
                for (int i = 0; i < 60; i++) {
                    minuteComboBox.addItem(i);
                }

                // Tạo JPanel để chứa các thành phần chọn giờ
                JPanel timePanel = new JPanel();
                timePanel.add(hourComboBox);
                timePanel.add(new JLabel(":"));
                timePanel.add(minuteComboBox);

                // Hiển thị hộp thoại chọn giờ
                int result = JOptionPane.showConfirmDialog(contentPane, timePanel, "Chọn giờ", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    int hour = (int) hourComboBox.getSelectedItem();
                    int minute = (int) minuteComboBox.getSelectedItem();

                    // Định dạng giờ thành chuỗi và hiển thị trong JTextField
                    String timeString = String.format("%02d:%02d:00", hour, minute);
                    textField_ChonGioTra.setText(timeString);
                }
            
			
			}
		});
		btnChonGioTra.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnChonGioTra.setBounds(393, 11, 190, 37);
		panel_ChonSan.add(btnChonGioTra);
		
		textField_ChonGioThue = new JTextField();
		textField_ChonGioThue.setEditable(false);
		textField_ChonGioThue.setColumns(10);
		textField_ChonGioThue.setBounds(193, 50, 190, 31);
		panel_ChonSan.add(textField_ChonGioThue);
		
		textField_ChonGioTra = new JTextField();
		textField_ChonGioTra.setEditable(false);
		textField_ChonGioTra.setColumns(10);
		textField_ChonGioTra.setBounds(393, 50, 190, 31);
		panel_ChonSan.add(textField_ChonGioTra);
		
		JLabel lblChonSan = new JLabel("Chọn sân");
		lblChonSan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChonSan.setBounds(10, 11, 145, 37);
		panel_ChonSan.add(lblChonSan);
		
		table_ChonSan = new JTable();
		table_ChonSan.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table_ChonSan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		bangChonSan = (DefaultTableModel) table_ChonSan.getModel();
		table_ChonSan.setRowHeight(30);
		bangChonSan.addColumn("Những sân đã đặt");
		bangChonSan.addColumn("Giờ thuê");
		bangChonSan.addColumn("Giờ trả");
		
		JScrollPane scrollPane_ChonSan = new JScrollPane(table_ChonSan);
		scrollPane_ChonSan.setBounds(10, 95, 922, 422);
		panel_ChonSan.add(scrollPane_ChonSan);
		
		JButton btn_OKchonsan = new JButton("OK");
		btn_OKchonsan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                DatSan();
			}
		});
		btn_OKchonsan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_OKchonsan.setBounds(691, 528, 116, 43);
		panel_ChonSan.add(btn_OKchonsan);
		
		JButton btn_CLEARchonsan = new JButton("Quay lại");
		btn_CLEARchonsan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_ChonGioThue.setText("");
				textField_ChonGioTra.setText("");
				tabbedPane.setSelectedIndex(2);
			}
		});
		btn_CLEARchonsan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_CLEARchonsan.setBounds(817, 528, 116, 43);
		panel_ChonSan.add(btn_CLEARchonsan);
		
		JPanel panel_DatSan = new JPanel();
		tabbedPane.addTab(null, null, panel_DatSan, null);
		panel_DatSan.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_DatSan.setLayout(null);
		
		JLabel lblChonLoaiSan = new JLabel("Chọn loại sân ");
		lblChonLoaiSan.setBounds(374, 35, 216, 43);
		lblChonLoaiSan.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel_DatSan.add(lblChonLoaiSan);
		
		String []cacLoaiSan = new String[] {" ","Sân 5", "Sân 6", "Sân 7"};

		comboBox_LoaiSan = new JComboBox<String>(cacLoaiSan);
		comboBox_LoaiSan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] jCbbox_chonSan5 = new String[] {"5A","5B","5C"};
				String[] jCbbox_chonSan6 = new String[] {"6A","6B"};
				String[] jCbbox_chonSan7 = new String[] {"7A","7B" };
				
				
				String loaiSanDaChon = (String) comboBox_LoaiSan.getSelectedItem();
				if(loaiSanDaChon.equals("Sân 5")) {
				   updateItemCombobox(jCbbox_chonSan5);
				} else if (loaiSanDaChon.equals("Sân 6")) {
					updateItemCombobox(jCbbox_chonSan6);
				} else if(loaiSanDaChon.equals("Sân 7")) {
					updateItemCombobox(jCbbox_chonSan7);
					
				}
				
			}

			private void updateItemCombobox(String[] options) {
                comboBox_ChonSan.removeAllItems();
                for (String option : options) {
					comboBox_ChonSan.addItem(option);
				}
			}
		});
		comboBox_LoaiSan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		comboBox_LoaiSan.setBounds(374, 78, 216, 55);
		panel_DatSan.add(comboBox_LoaiSan);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		panel_DatSan.add(table);
		
		JButton btn_OKdatsan = new JButton("OK");
		btn_OKdatsan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btn_OKdatsan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
				    	JDBCUtil.getConnection();
				    	String luaChonSan = (String) comboBox_LoaiSan.getSelectedItem();
				    	int luaChon = 0 ;
				    	if(luaChonSan.equals("Sân 5")) {
				    		luaChon = 5;
				    	} else if (luaChonSan.equals("Sân 6")) {
				    		luaChon = 6;
				    	} else if (luaChonSan.equals("Sân 7")) {
				    		luaChon = 7;
				    	}
				        String sql = "SELECT  TenSan,TGChoThue, TGTra FROM san s "
				        		+ "INNER JOIN datsan d ON s.IDsan = d.IDsan "
				        		+ "WHERE d.IDsan LIKE '"+luaChon+ "%' "
				        		+ "AND Ngay = '" +textField_chonNgay.getText()+ "'";
						PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql);
						ResultSet rss = preparedStatement.executeQuery(sql);
						
						bangChonSan.setRowCount(0);
						
						while (rss.next()) {
							String data1 = rss.getString("TenSan");
							String data2 = rss.getString("TGChoThue");
							String data3 = rss.getString("TGTra");
							
							Object[] row = new Object[] {data1, data2, data3};
							
							
							bangChonSan.addRow(row);
							}
							rss.close();
							preparedStatement.close();
							JDBCUtil.closeConnection(JDBCUtil.getConnection());
							
							
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				tabbedPane.setSelectedIndex(1);
			}
		});
		btn_OKdatsan.setBounds(737, 528, 94, 43);
		panel_DatSan.add(btn_OKdatsan);
		
		JButton btn_DONGdatsan = new JButton("Đóng");
		btn_DONGdatsan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btn_DONGdatsan.setBounds(841, 528, 94, 43);
		panel_DatSan.add(btn_DONGdatsan);
		
		
		
		JButton btnChonNgay = new JButton("Chọn ngày");
		btnChonNgay.setBounds(28, 36, 226, 43);
		panel_DatSan.add(btnChonNgay);
		btnChonNgay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

                // Tạo JComboBox cho ngày
                JComboBox<Integer> dayComboBox = new JComboBox<>();
                for (int i = 1; i <= 31; i++) {
                    dayComboBox.addItem(i);
                }

                // Tạo JComboBox cho tháng
                String[] monthNames = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6",
                        "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
                JComboBox<String> monthComboBox = new JComboBox<>(monthNames);

                // Tạo JSpinner cho năm
                SpinnerModel yearSpinnerModel = new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1);
                JSpinner yearSpinner = new JSpinner(yearSpinnerModel);

                // Tạo JPanel để chứa các thành phần chọn ngày
        		JPanel panelDate = new JPanel();
               panelDate.add(dayComboBox);
               panelDate.add(monthComboBox);
               panelDate.add(yearSpinner);

                // Hiển thị hộp thoại chọn ngày
                int result = JOptionPane.showConfirmDialog(contentPane, panelDate, "Chọn ngày", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    int day = (int) dayComboBox.getSelectedItem();
                    int month = monthComboBox.getSelectedIndex() + 1;
                    int year = (int) yearSpinner.getValue();

                    // Định dạng ngày thành chuỗi và hiển thị trong JTextField
                    String dateString = String.format( "%04d-%02d-%02d", year, month, day);
                    textField_chonNgay.setText(dateString);
                }
            
			}
		});
		btnChonNgay.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		textField_chonNgay = new JTextField();
		textField_chonNgay.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textField_chonNgay.setBounds(28, 78, 226, 43);
		panel_DatSan.add(textField_chonNgay);
		textField_chonNgay.setColumns(10);
		
		JPanel panel_Khachhang = new JPanel();
		tabbedPane.addTab("New tab", null, panel_Khachhang, null);
		panel_Khachhang.setLayout(null);
		
		JLabel lbl_thongtincanhan = new JLabel("Nhập thông tin cá nhân ");
		lbl_thongtincanhan.setForeground(new Color(0, 0, 255));
		lbl_thongtincanhan.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_thongtincanhan.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbl_thongtincanhan.setBounds(286, 62, 498, 91);
		panel_Khachhang.add(lbl_thongtincanhan);
		
		JLabel lbl_socancuoc = new JLabel("Số căn cước :");
		lbl_socancuoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_socancuoc.setBounds(10, 200, 155, 51);
		panel_Khachhang.add(lbl_socancuoc);
		
		JLabel lbl_hovaten = new JLabel("Họ và tên :");
		lbl_hovaten.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_hovaten.setBounds(10, 267, 155, 51);
		panel_Khachhang.add(lbl_hovaten);
		
		JLabel lbl_sdt = new JLabel("Số điện thoại :");
		lbl_sdt.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_sdt.setBounds(10, 339, 155, 51);
		panel_Khachhang.add(lbl_sdt);
		
		textField_cccd = new JTextField();
		textField_cccd.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField_cccd.setBounds(186, 200, 661, 44);
		panel_Khachhang.add(textField_cccd);
		textField_cccd.setColumns(10);
		
		textField_hovaten = new JTextField();
		textField_hovaten.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField_hovaten.setColumns(10);
		textField_hovaten.setBounds(186, 267, 661, 44);
		panel_Khachhang.add(textField_hovaten);
		
		textField_sdt = new JTextField();
		textField_sdt.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(186, 339, 661, 44);
		panel_Khachhang.add(textField_sdt);
		
		JButton btn_Xacnhan = new JButton("Xác nhận");
		btn_Xacnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_cccd.getText().isEmpty()&& textField_hovaten.getText().isEmpty() && textField_sdt.getText().isEmpty()) {
					
				} else {
					tabbedPane.setSelectedIndex(2);
				}
			}
		});
		btn_Xacnhan.setFont(new Font("Tahoma", Font.BOLD, 28));
		btn_Xacnhan.setBounds(733, 500, 181, 51);
		panel_Khachhang.add(btn_Xacnhan);
		
		JButton btn_TimKiem = new JButton("Tìm sân");
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timSan();
			}
		});
		btn_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btn_TimKiem.setBounds(971, 436, 215, 53);
		contentPane.add(btn_TimKiem);
		
		JTextArea txtrThngTinSn = new JTextArea();
		txtrThngTinSn.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtrThngTinSn.setText("\r\n THÔNG TIN SÂN BÓNG\r\n. Thời gian mở cửa  \r\n     5:00 - 21:00\r\n. Có 3 loại sân \r\n- Sân 5 (5A, 5B, 5C) \r\n    200.000 VND/h\r\n- Sân 6 (6A, 6B)        \r\n   250.000 VND/h\r\n- Sân 7 (7A, 7B)        \r\n   300.000 VND/h\r\n. Hotline : 0834595399  ");
		txtrThngTinSn.setBounds(971, 11, 215, 347);
		txtrThngTinSn.setEditable(false);
		contentPane.add(txtrThngTinSn);

	}

	
	public void DatSan() {
		JDBCUtil.getConnection();
		String IDkhachhang = textField_cccd.getText();
		String HovaTen = textField_hovaten.getText();
		String SDT = textField_sdt.getText();
		String ngayThue = textField_chonNgay.getText();
		String idSan = (String) comboBox_ChonSan.getSelectedItem();
		String gioThue = textField_ChonGioThue.getText();
		String gioTra = textField_ChonGioTra.getText();
		String gioThueString = ngayThue + " " + gioThue;
		String gioTraString = ngayThue + " " + gioTra;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String luaChonSan = (String) comboBox_LoaiSan.getSelectedItem();
		String sql1 = "SELECT COUNT(*) FROM "
				      + "(SELECT * FROM datsan "
				      + "WHERE IDsan = '"+idSan+"' AND Ngay = '"+ngayThue+"') AS subquery "
				    + "WHERE (TGChoThue <= '"+gioThue+"' AND TGTra > '"+gioThue+"') "
				       + "OR (TGChoThue < '"+gioTra+"' AND TGTra >='"+gioTra+"')"
				       + "OR (TGChoThue > '"+gioThue+"' AND  TGChoThue < '"+gioTra+"')";
		try {
			PreparedStatement prestatement1 = JDBCUtil.getConnection().prepareStatement(sql1);
			ResultSet rss = prestatement1.executeQuery();
			int count = 0;
				if(rss.next()) {
					count = rss.getInt(1);	
				}
				  if(count == 0) {
				      try {
				         java.util.Date tgThue = dateFormat.parse(gioThueString);
				         java.util.Date tgTra = dateFormat.parse(gioTraString);
			 	         Calendar batDau = Calendar.getInstance();          
			             batDau.setTime(tgThue);
				         Calendar ketThuc = Calendar.getInstance();
				         ketThuc.setTime(tgTra);
				         long startMilis = batDau.getTimeInMillis();
				         long endMilis = ketThuc.getTimeInMillis();
				
				         long duration = endMilis - startMilis;
				
				         int giaSan5 = (int) ((duration*200000)/ 3600000);
				         int giaSan6 = (int) ((duration*250000)/ 3600000);
				         int giaSan7 = (int) ((duration*300000)/ 3600000);
				         int giaPhaiTra = 0;
				         int i = 0;
				         if (luaChonSan.equals("Sân 5")) {
							 i = JOptionPane.showConfirmDialog(contentPane,"Số tiền thanh toán là : "+ giaSan5+ " VND" , "Xác nhận đặt sân", JOptionPane.OK_CANCEL_OPTION);
							giaPhaiTra = giaSan5;
						} else  if (luaChonSan.equals("Sân 6")) {
							 i = JOptionPane.showConfirmDialog(contentPane,"Số tiền thanh toán là : "+ giaSan6+ " VND" , "Xác nhận đặt sân", JOptionPane.OK_CANCEL_OPTION);
							giaPhaiTra = giaSan6;
						} else  if (luaChonSan.equals("Sân 7")) {
							 i = JOptionPane.showConfirmDialog(contentPane,"Số tiền thanh toán là : "+ giaSan7+ " VND" , "Xác nhận đặt sân", JOptionPane.OK_CANCEL_OPTION);
							giaPhaiTra = giaSan7;
						}
				         if (i == JOptionPane.CANCEL_OPTION) {
							
						} else {
							String sql = "INSERT INTO datsan (IDkhachhang,HovaTen, SDT, IDsan, Ngay, TGChoThue, TGTra, ThanhToan)"
								           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
								
								  try {
									 PreparedStatement prestatement = JDBCUtil.getConnection().prepareStatement(sql);
									 prestatement.setString(1, IDkhachhang);
									 prestatement.setString(2,HovaTen);
									 prestatement.setString(3, SDT);
									 prestatement.setString(4, idSan);
									 prestatement.setDate(5, Date.valueOf(ngayThue));
									 prestatement.setTime(6, Time.valueOf(gioThue));
									 prestatement.setTime(7, Time.valueOf(gioTra));
									 prestatement.setInt(8, giaPhaiTra);
									 
									 int ketQua = prestatement.executeUpdate();
									if(ketQua != 0) {
										 JOptionPane.showMessageDialog(this, "Đặt sân thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
									 textField_chonNgay.setText("");
									 textField_ChonGioThue.setText("");
									 textField_ChonGioTra.setText("");
									 textField_cccd.setText("");
									 textField_hovaten.setText("");
									 textField_sdt.setText("");
									}
									prestatement.close();
									 JDBCUtil.closeConnection(JDBCUtil.getConnection());
								 } catch (SQLException e) {
									JOptionPane.showMessageDialog(this, "Đặt sân không thành công ", "Lỗi", JOptionPane.ERROR_MESSAGE);
								 }
						}
					prestatement1.close();
				
			      	} catch (ParseException e) {
					     e.printStackTrace();
				    }
				 
				 } else {
						JOptionPane.showMessageDialog(this, "Sân không khả dụng ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	JDBCUtil.closeConnection(JDBCUtil.getConnection());
		
	}
	public void xemThongTin() {	    
	    try {
	    	JDBCUtil.getConnection();
	        String sql = "SELECT ID, HovaTen, SDT, IDsan, Ngay, TGChoThue, TGTra, ThanhToan FROM datsan ";
			PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql);
			ResultSet rss = preparedStatement.executeQuery(sql);
			
			bangThongTin.setRowCount(0);
			
			while (rss.next()) {
				String data1 = rss.getString("ID");
				String data2 = rss.getString("HovaTen");
				String data3 = rss.getString("SDT");
				String data4 = rss.getString("IDsan");
				String data5 = rss.getString("Ngay");
				String data6 = rss.getString("TGChoThue");
				String data7 = rss.getString("TGTra");
				String data8 = rss.getString("ThanhToan");
				
				Object[] row = new Object[] {data1, data2, data3, data4, data5, data6, data7,data8};
				
				bangThongTin.addRow(row);
				}
				rss.close();
				preparedStatement.close();
				JDBCUtil.closeConnection(JDBCUtil.getConnection());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void timSan() {
		 try {
		    	JDBCUtil.getConnection();
		    	String ID = JOptionPane.showInputDialog(this,"Nhập mã đặt sân :");
		        String sql = "SELECT ID, HovaTen, SDT, IDsan, Ngay, TGChoThue, TGTra, ThanhToan "
		        		   + "FROM datsan "
		        		   + "WHERE ID = '"+ID+"'";
				PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql);
				ResultSet rss = preparedStatement.executeQuery(sql);
				
				bangThongTin.setRowCount(0);
				
				if (rss.next()) {
					while (rss.next()) {
					String data1 = rss.getString("ID");
					String data2 = rss.getString("HovaTen");
					String data3 = rss.getString("SDT");
					String data4 = rss.getString("IDsan");
					String data5 = rss.getString("Ngay");
					String data6 = rss.getString("TGChoThue");
					String data7 = rss.getString("TGTra");
					String data8 = rss.getString("ThanhToan");
					
					Object[] row = new Object[] {data1, data2, data3, data4, data5, data6, data7,data8};
					
					bangThongTin.addRow(row);
					}
					rss.close();
					preparedStatement.close();
					JDBCUtil.closeConnection(JDBCUtil.getConnection());
				
				} else {
					JOptionPane.showMessageDialog(this, "Mã đặt sân không tồn tại");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void huySan() {
		try {
			String ID = JOptionPane.showInputDialog(this,"Nhập mã đặt sân :");
			String canCuoc = JOptionPane.showInputDialog(this,"Nhập số căn cước :");
			JDBCUtil.getConnection();
			String sql = "DELETE FROM datsan WHERE ID = "+ID+" AND IDkhachhang = '"+canCuoc+"' ";
			PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql);
			int ketQua = preparedStatement.executeUpdate();
			if(ketQua != 0){
				JOptionPane.showMessageDialog(this, "Hủy sân thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Hủy sân không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

			preparedStatement.close();
			JDBCUtil.closeConnection(JDBCUtil.getConnection());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Hủy sân không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
}


