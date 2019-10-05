package Application;

import maze.Chap;
import maze.Key;
import maze.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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


	private JButton east;
	private JButton north;
	private JButton south;
	private JPanel dicePanel;

	private JTextArea textArea = new JTextArea(5, 0);

	private JPanel board;
	private JPanel outerMostPanel;
	private JLabel playerName;

	public GraphicalUserInterface() {
		super("CHAP GAME");
		game = new Game();
		initialize();
		setupGUI();
		getContentPane().add(outerMostPanel);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		// setupPlayers();
		// game = new CluedoGame(players);

		// game.newGame();
		// updatePanel();

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
		start = new JMenuItem("Start Game");
		end = new JMenuItem("End Game");
		option = new JMenuItem("Option");
		level = new JMenuItem("Level");
		help = new JMenuItem("Help");
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");

		board = new JPanel();
		outerMostPanel = new JPanel();
	}

	public void setupGUI() {
		start.addActionListener(e -> {

		});
		end.addActionListener(e -> {

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

		//System.out.println(game.getBoard().toString());
		output.setText(game.getBoard().toString());
		board.add(output);
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
		outerMostPanel.add(boardandstatus,BorderLayout.CENTER);
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
			//game.move(Direction.WEST);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			//game.move(Direction.WEST);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			//game.move(Direction.WEST);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			//game.move(Direction.WEST);
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
