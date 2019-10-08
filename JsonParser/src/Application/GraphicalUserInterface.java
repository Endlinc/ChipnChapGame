package Application;

import Persistence.JsonLoader;
import Persistence.JsonSaver;
import maze.Chap;
import maze.Key;
import maze.Tile;
import Renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GraphicalUserInterface extends JFrame implements WindowListener {
	private Game game;
	private JMenu gameMenu;
	private JMenuItem start;
	private JMenuItem end;
	private JMenu optionMenu;
	private JMenuItem option;
	private JMenu levelMenu;
	private JMenuItem level;
	private JMenu helpMenu;
	private JMenuItem help;
	private JMenu saveMenu;
	private JMenuItem save;
	private JMenuItem load;
	protected Render render = new Render();

	private JButton east;
	private JButton north;
	private JButton south;
	private JPanel dicePanel;

	private JTextArea textArea = new JTextArea(5, 0);

	private JPanel board;
	private JPanel outerMostPanel;
	private RenderPanel renderer;
	private JLabel playerName;
	private KeyListener k;

	public GraphicalUserInterface() {
		super("CHAP GAME");
		//game = new Game();
		initialize();
		setupGUI();
		getContentPane().add(outerMostPanel);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		addKeyListener(renderer);

		pack();

		// Finally, make the window visible
		setVisible(true);
	}

	public void initialize() {
		gameMenu = new JMenu("Game");
		optionMenu = new JMenu("Option");
		levelMenu = new JMenu("Level");
		helpMenu = new JMenu("Help");
		saveMenu = new JMenu("Save&Load");
		start = new JMenuItem("New Game");
		end = new JMenuItem("End Game");
		option = new JMenuItem("Option");
		level = new JMenuItem("Level");
		help = new JMenuItem("Help");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");

		board = new JPanel();
		renderer = new RenderPanel(game) {
			public void paintComponent(Graphics g) {
				if(game!=null){
					super.paintComponent(g);
					Graphics2D g2 = (Graphics2D) g;
					render.renderGame(g2, getWidth(), getHeight(), game);
				}
			}
		};
		renderer.setPreferredSize(new java.awt.Dimension(50, 512));

		outerMostPanel = new JPanel();
	}

	public void setupGUI() {
		start.addActionListener(e -> {
			game = new Game();
			renderer.newGame(game);
			renderer.repaint();
		});
		end.addActionListener(e -> {

		});
		save.addActionListener(e -> {
			new JsonSaver().makeJson(game);
		});
		load.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = fileChooser.showOpenDialog(outerMostPanel);
			if (result == JFileChooser.APPROVE_OPTION) {
				// get the file path
				File selectedFile = fileChooser.getSelectedFile();
				// reload file to Game
				game = new JsonLoader().importJson(selectedFile);
				renderer.newGame(game);
				renderer.repaint();
			}
		});
		gameMenu.add(start);
		gameMenu.add(end);

		optionMenu.add(option);
		levelMenu.add(level);
		helpMenu.add(help);
		saveMenu.add(save);
		saveMenu.add(load);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new GridLayout(1, 5));
		menuBar.add(gameMenu);
		menuBar.add(optionMenu);
		menuBar.add(levelMenu);
		menuBar.add(helpMenu);
		menuBar.add(saveMenu);
		setJMenuBar(menuBar);

		JPanel status = new JPanel();
		JPanel level = new JPanel();
		JPanel time = new JPanel();
		JPanel chipleft = new JPanel();
		status.add(level, BorderLayout.NORTH);
		status.add(time, BorderLayout.CENTER);
		status.add(chipleft, BorderLayout.SOUTH);
		JSplitPane boardandstatus = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		JTextArea output = new JTextArea(5,0);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setEditable(true);
		JScrollPane scroll = new JScrollPane(output);

		renderer.setVisible(true);
		board.add(renderer);
		JComponent drawing = new JComponent() {
			protected void paintComponent(Graphics g){
				redraw(g);
			}
			private void redraw(Graphics g){
				//renderWindow.redraw(game.getCurrentRoom(), GameWorld.Direction.NORTH, g);
			}
		};

		boardandstatus.setDividerSize(5);
		boardandstatus.setContinuousLayout(true);
		boardandstatus.setResizeWeight(1);
		boardandstatus.setBorder(BorderFactory.createEmptyBorder());

		boardandstatus.setLeftComponent(board);
		boardandstatus.setRightComponent(status);
		outerMostPanel.setSize(1000, 800);
		outerMostPanel.setLayout(new BorderLayout());
		//outerMostPanel.add(boardandstatus,BorderLayout.CENTER);
		outerMostPanel.add(renderer,BorderLayout.CENTER);
	}

	public void windowClosing(WindowEvent windowEvent) {
		// ask user to confirm they wanted to do this
		int dialog = JOptionPane.showConfirmDialog(this, new JLabel("Exit Game?"), "Confirm Exit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (dialog == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent windowEvent) {

	}

	@Override
	public void windowClosed(WindowEvent windowEvent) {

	}

	@Override
	public void windowIconified(WindowEvent windowEvent) {

	}

	@Override
	public void windowDeiconified(WindowEvent windowEvent) {

	}

	@Override
	public void windowActivated(WindowEvent windowEvent) {

	}

	@Override
	public void windowDeactivated(WindowEvent windowEvent) {

	}

	public void MouseListener() {
	}


	public void KeyListener(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.movePlayer(game.getChap(), Game.Direction.WEST);
			renderer.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.movePlayer(game.getChap(), Game.Direction.EAST);
			renderer.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			game.movePlayer(game.getChap(), Game.Direction.NORTH);
			renderer.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			game.movePlayer(game.getChap(), Game.Direction.SOUTH);
			renderer.repaint();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
			return;
		} else if ((e.getKeyCode() == KeyEvent.VK_X) && (e.isControlDown())) {
			return;
		} else if ((e.getKeyCode() == KeyEvent.VK_S) && (e.isControlDown())) {
			return;
		} else if ((e.getKeyCode() == KeyEvent.VK_R) && (e.isControlDown())) {
			return;
		} else if ((e.getKeyCode() == KeyEvent.VK_P) && (e.isControlDown())) {
			return;
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			return;
		} else {
			System.out.println("Wrong Keyword input");
		}
	}

//	public boolean unlockPassage(Chap p, Tile t) {
//		Boolean exit = getCurrentRoom().exits[dir.ordinal()];
//		if (exit != null && exit == true) { // check the exit exists and is locked
//			// Check you have a key for the passage
//			List<Item> inv = player.getInventory();
//			for (Item i : inv) {
//				if (i instanceof Key) {
//					exit = false;
//					inv.remove(i);
//					return true;
//				}
//			}
//		}
//		return false;
//	}

}
