package org.targol.cncsurface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

/**
 * This class describes ...
 *
 * @author Targol Lagadec
 *
 */
public class CncSurface extends JFrame {

	private JPanel		contentPane;
	private JLabel		lblCncSurfacerEnter;
	private JPanel		paramsPanel;
	private JLabel		lblToolDiameter;
	private JSpinner	toolDiameterSpiner;
	private JLabel		lblSurfacingDepth;
	private JLabel		lblSurfacingWidth;
	private JLabel		lblSurfacingLength;
	private JButton		btnGenerateGcode;
	private JPanel		panel_1;
	private JSpinner	surfacingDepthSpinner;
	private JSpinner	surfaceWidthSpinner;
	private JSpinner	surfaceLengthSpinner;
	private JTextArea	tfExplain;
	private JButton		btnSaveGcodeFile;
	private JScrollPane	scrollGCode;
	private JTextArea	txtGCode;
	private Action		action;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final CncSurface frame = new CncSurface();
					frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CncSurface() {
		initialize();
	}

	private void initialize() {
		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(new BorderLayout());
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(getLblCncSurfacerEnter(), BorderLayout.NORTH);
		this.contentPane.add(getPanel_1(), BorderLayout.WEST);
		this.contentPane.add(getScrollGCode(), BorderLayout.CENTER);
	}

	private JLabel getLblCncSurfacerEnter() {
		if (this.lblCncSurfacerEnter == null) {
			this.lblCncSurfacerEnter = new JLabel(
					"CNC Surfacer.\n Enter your dimensions on the left panel and click on \"generate\".\n All dimensions are in mm.");
		}
		return this.lblCncSurfacerEnter;
	}

	private JPanel getPanel_1() {
		if (this.panel_1 == null) {
			this.panel_1 = new JPanel();
			this.panel_1.setLayout(new BorderLayout(0, 0));
			this.panel_1.add(getParamsPanel(), BorderLayout.NORTH);
			this.panel_1.add(getTfExplain(), BorderLayout.CENTER);
		}
		return this.panel_1;
	}

	private JPanel getParamsPanel() {
		if (this.paramsPanel == null) {
			this.paramsPanel = new JPanel();
			this.paramsPanel.setLayout(new GridLayout(5, 2, 10, 15));
			this.paramsPanel.add(getLblToolDiameter());
			this.paramsPanel.add(getToolDiameterSpinner());
			this.paramsPanel.add(getLblSurfacingDepth());
			this.paramsPanel.add(getSurfacingDepthSpinner());
			this.paramsPanel.add(getLblSurfacingWidth());
			this.paramsPanel.add(getSurfaceWidthSpinner());
			this.paramsPanel.add(getLblSurfacingLength());
			this.paramsPanel.add(getSurfaceLengthSpinner());
			this.paramsPanel.add(getBtnGenerateGcode());
			this.paramsPanel.add(getBtnSaveGcodeFile());
		}
		return this.paramsPanel;
	}

	private JLabel getLblToolDiameter() {
		if (this.lblToolDiameter == null) {
			this.lblToolDiameter = new JLabel("Tool diameter");
		}
		return this.lblToolDiameter;
	}

	private JSpinner getToolDiameterSpinner() {
		if (this.toolDiameterSpiner == null) {
			this.toolDiameterSpiner = new JSpinner();
			this.toolDiameterSpiner.setModel(new SpinnerNumberModel(10.0, 1.0, 20.0, 0.1));
		}
		return this.toolDiameterSpiner;
	}

	private JLabel getLblSurfacingDepth() {
		if (this.lblSurfacingDepth == null) {
			this.lblSurfacingDepth = new JLabel("Surfacing depth");
		}
		return this.lblSurfacingDepth;
	}

	private JSpinner getSurfacingDepthSpinner() {
		if (this.surfacingDepthSpinner == null) {
			this.surfacingDepthSpinner = new JSpinner();
			this.surfacingDepthSpinner.setModel(new SpinnerNumberModel(0.1, 0.1, 3.0, 0.1));
		}
		return this.surfacingDepthSpinner;
	}

	private JLabel getLblSurfacingWidth() {
		if (this.lblSurfacingWidth == null) {
			this.lblSurfacingWidth = new JLabel("Surfacing width (Y axis)");
		}
		return this.lblSurfacingWidth;
	}

	private JSpinner getSurfaceWidthSpinner() {
		if (this.surfaceWidthSpinner == null) {
			this.surfaceWidthSpinner = new JSpinner();
			this.surfaceWidthSpinner.setModel(new SpinnerNumberModel(50.0, 30.0, 300.0, 10.0));
		}
		return this.surfaceWidthSpinner;
	}

	private JLabel getLblSurfacingLength() {
		if (this.lblSurfacingLength == null) {
			this.lblSurfacingLength = new JLabel("Surfacing length (X axis)");
		}
		return this.lblSurfacingLength;
	}

	private JSpinner getSurfaceLengthSpinner() {
		if (this.surfaceLengthSpinner == null) {
			this.surfaceLengthSpinner = new JSpinner();
			this.surfaceLengthSpinner.setModel(new SpinnerNumberModel(50.0, 30.0, 500.0, 10.0));
		}
		return this.surfaceLengthSpinner;
	}

	private JButton getBtnGenerateGcode() {
		if (this.btnGenerateGcode == null) {
			this.btnGenerateGcode = new JButton("Generate Gcode");
			this.btnGenerateGcode.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					generateGCode();
				}
			});
		}
		return this.btnGenerateGcode;
	}

	/**
	 *
	 */
	private JButton getBtnSaveGcodeFile() {
		if (this.btnSaveGcodeFile == null) {
			this.btnSaveGcodeFile = new JButton("Save Gcode file");
			this.btnSaveGcodeFile.setEnabled(false);
			this.btnSaveGcodeFile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					saveGCode();
				}
			});
		}
		return this.btnSaveGcodeFile;
	}

	private JTextArea getTfExplain() {
		if (this.tfExplain == null) {
			this.tfExplain = new JTextArea();
			this.tfExplain.setMargin(new Insets(10, 10, 10, 10));
			this.tfExplain.setEditable(false);
			this.tfExplain.setLineWrap(true);
			this.tfExplain.setWrapStyleWord(true);
			this.tfExplain.setMaximumSize(new Dimension(100, 2147483647));
			this.tfExplain.setPreferredSize(new Dimension(100, 200));
			final String explain = "This tool will start from (0.0) position on \"bottom left\" and will surface the material in a single pass.\n"
					+ "Surfacing will be made realizing a \"snake\" paterne going max Y, raising a bit X, going 0 Y, raising a bit X, etc...\n"
					+ "Check generated code before running on tyour CNC !!!";
			this.tfExplain.setText(explain);
		}
		return this.tfExplain;
	}

	private JScrollPane getScrollGCode() {
		if (this.scrollGCode == null) {
			this.scrollGCode = new JScrollPane(this.txtGCode, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			this.scrollGCode.setViewportView(getTxtGCode());
			this.scrollGCode.setAutoscrolls(true);
		}
		return this.scrollGCode;
	}

	private JTextArea getTxtGCode() {
		if (this.txtGCode == null) {
			this.txtGCode = new JTextArea();
			this.txtGCode.setFont(new Font("FreeMono", Font.BOLD, 16));
		}
		return this.txtGCode;
	}

	protected void generateGCode() {
		final double toolDiam = (double) this.toolDiameterSpiner.getValue();
		final double depth = (double) this.surfacingDepthSpinner.getValue();
		final double width = (double) this.surfaceWidthSpinner.getValue();
		final double length = (double) this.surfaceLengthSpinner.getValue();
		final String resultGCode = GCodeWriter.generateGcode(toolDiam, depth, width, length);
		this.txtGCode.setText(resultGCode);
		this.btnSaveGcodeFile.setEnabled(true);
	}

	protected void saveGCode() {
		final String resultGCode = this.txtGCode.getText();
		final JFileChooser fileChooser = new JFileChooser();
		final int option = fileChooser.showSaveDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			final File file = fileChooser.getSelectedFile();
			try {
				// attach a file to FileWriter
				final FileWriter fw = new FileWriter(file);
				// read each character from string and write
				// into FileWriter
				for (int i = 0; i < resultGCode.length(); i++) {
					fw.write(resultGCode.charAt(i));
				}
				fw.close();
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

}
