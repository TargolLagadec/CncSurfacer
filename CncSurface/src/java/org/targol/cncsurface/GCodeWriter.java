package org.targol.cncsurface;

/**
 * This class describes ...
 *
 * @author Targol Lagadec
 *
 */
public class GCodeWriter {
	private static final String INIT = "G21 G90 G64 G40\n G0 Z3.0\n ( T0 : 0.0 )\n T0 M6\n ( Gravure1 )\n G17\n M3 S1000\n ";

	/**
	 *
	 * @param toolDiam tool diameter.
	 * @param depth surfacing depth.
	 * @param width surface witdh (Y axis).
	 * @param length surface length (X axis).
	 * @return GCode to surface.
	 */
	public static String generateGcode(final double toolDiam, final double depth, final double width, final double length) {
		String ret = INIT;
		final double xMin = toolDiam / 2;
		final double yMin = toolDiam / 2;
		final double xMax = length - toolDiam / 2;
		final double yMax = width - toolDiam / 2;
		double xCurrent = xMin;
		boolean move0toMax = true;

		// Moving to (x0, y0) => [0+(tooldiameter /2),0+(tooldiameter /2)
		ret = ret.concat("G0 X").concat(Double.toString(xMin)).concat(" Y").concat(Double.toString(yMin)).concat("\n");
		// Plunging to surfacing depth
		ret = ret.concat("G1 F300.0 Z-").concat(Double.toString(depth)).concat("\n");
		while (xCurrent + toolDiam / 2 < xMax) {
			if (move0toMax) {
				ret = ret.concat("G1 F500.0 Y").concat(Double.toString(yMax)).concat("\n");
				move0toMax = false;
			} else {
				ret = ret.concat("G1 F500.0 Y").concat(Double.toString(yMin)).concat("\n");
				move0toMax = true;
			}
			xCurrent = xCurrent + toolDiam / 2;
			ret = ret.concat("G1 F500.0 X").concat(Double.toString(xCurrent)).concat("\n");
		}
		xCurrent = xMax;
		ret = ret.concat("G1 F500.0 X").concat(Double.toString(xCurrent)).concat("\n");
		if (move0toMax) {
			ret = ret.concat("G1 F500.0 Y").concat(Double.toString(yMax)).concat("\n");
			move0toMax = false;
		} else {
			ret = ret.concat("G1 F500.0 Y").concat(Double.toString(yMin)).concat("\n");
			move0toMax = true;
		}
		// Raising CNC and stopping.
		ret = ret.concat("G0 Z3.0\n").concat("M5\n").concat("M30");
		return ret;
	}
}
