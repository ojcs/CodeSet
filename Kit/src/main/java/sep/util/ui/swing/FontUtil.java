package sep.util.ui.swing;

import static java.awt.Font.PLAIN;

import java.awt.Font;

import javax.swing.UIManager;

public final class FontUtil {
	public static final Font NSimSun12 = new Font("NSimSun", PLAIN, 12);
	
	public static void setAllFont(final Font font) {
		for (final String componentName : new String[] {
			"Button.font", "CheckBox.font", "CheckBoxMenuItem.acceleratorFont", "CheckBoxMenuItem.font",
			"ColorChooser.font", "ComboBox.font", "DesktopIcon.font", "EditorPane.font",
			"FormattedTextField.font", "InternalFrame.titleFont", "Label.font", "List.font",
			"Menu.acceleratorFont", "Menu.font", "MenuBar.font", "MenuItem.acceleratorFont",
			"MenuItem.font", "OptionPane.font", "Panel.font", "PasswordField.font", "PopupMenu.font",
			"ProgressBar.font", "RadioButton.font", "RadioButtonMenuItem.acceleratorFont",
			"RadioButtonMenuItem.font", "ScrollPane.font", "Spinner.font", "TabbedPane.font",
			"Table.font", "TableHeader.font", "TextArea.font", "TextField.font", "TextPane.font",
			"TitledBorder.font", "ToggleButton.font", "ToolBar.font", "ToolTip.font", "Tree.font",
			"Viewport.font"
		}) {
			UIManager.put(componentName, font);
		}
	}
}
