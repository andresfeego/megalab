package interfaces_Modificadas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer.UIResource;
import javax.swing.text.View;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class customTabbedPaneUI extends BasicTabbedPaneUI {

	// Para almacenar la forma del tabs
	private Polygon shape;
	// Para almacenar los puntos de la forma poligonal del tabs o.O
	private int xp[] = null;
	private int yp[] = null;

	@Override
	protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
		
		g.setFont(font);
		View textView = getTextViewForTab(tabIndex);
		if (textView != null) {
			textView.paint(g, textRect);
			return;
		}

		int ascent = metrics.getAscent();

		int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
		if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
			Color fg = Color.black;
			if (isSelected) {
				Color selectionForeground = Colores.clrTextoPrincipal;
				if (selectionForeground != null)
					fg = selectionForeground;
			}
			g.setColor(fg);

			if (mnemIndex != -1)
				BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + ascent);
			else
				g.drawString(title, textRect.x, textRect.y + ascent);
		} else {
			Color bg = Colores.clrTextoInactivo;
			g.setColor(bg);
			if (mnemIndex != -1)
				BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + ascent);
			else
				g.drawString(title, textRect.x, textRect.y + ascent);

			g.setColor(bg);
			if (mnemIndex != -1)
				BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x + 1, textRect.y + 1 + ascent);
			else
				g.drawString(title, textRect.x + 1, textRect.y + 1 + ascent);
		}
	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		Graphics2D g2D = (Graphics2D) g;
		// colores degradados para los tabs
		GradientPaint gradientSel = new GradientPaint(0, 0, new Color(242, 249, 242), 0, y + h / 2, new Color(217, 237, 246));
		GradientPaint gradientUnsel = new GradientPaint(0, 0, new Color(232, 232, 232), 0, y + h / 2, new Color(205, 205, 205));

		switch (tabPlacement) {
		case LEFT:
		case RIGHT:
		case BOTTOM:
			/* codigo para estos tabs */
			break;
		case TOP:
		default:
			xp = new int[] { x, x, x + 4, x + w + 5, x + w + 5, x + w, x + w, x };
			yp = new int[] { y + h, y + 4, y, y, y + 5, y + 10, y + h, y + h };
			break;
		}

		shape = new Polygon(xp, yp, xp.length);

		if (isSelected) {
			g2D.setColor(Colores.clrPrincipal);
		} else {
			g2D.setColor(Colores.clrFondo);
		}

		g2D.fill(shape);
	}

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {

		Graphics2D g2D = (Graphics2D) g;

		switch (tabPlacement) {
		case LEFT:
		case RIGHT:
		case BOTTOM:
			/* codigo para estos tabs */
			break;

		case TOP:
		default:
			xp = new int[] { x, x, x + 4, x + w + 5, x + w + 5, x + w, x + w, x };
			yp = new int[] { y + h, y + 4, y, y, y + 5, y + 10, y + h, y + h };
			break;
		}

		shape = new Polygon(xp, yp, xp.length);

		if (isSelected) {
			g2D.setColor(Colores.clrSecundario);
			g2D.drawPolyline(xp, yp, xp.length - 1);
		} else {
			g2D.setColor(Colores.clrTextoInactivo);
			g2D.drawPolyline(xp, yp, xp.length);
		}

	}

}// --> fin clase UI
