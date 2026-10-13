package edu.luc.etl.cs313.android.shapes.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import edu.luc.etl.cs313.android.shapes.model.*;

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
public class Draw implements Visitor<Void> {

    // TODO entirely your job (except onCircle)

    private final Canvas canvas;

    private final Paint paint;

    public Draw(final Canvas canvas, final Paint paint) {
        this.canvas = canvas; // Changed from null to canvas
        this.paint = paint; // Changed from null to paint, which will be the default color
        paint.setStyle(Style.STROKE);
        // should the default color always be retained after calling a method?
    }

    @Override
    public Void onCircle(final Circle c) {
        canvas.drawCircle(0, 0, c.getRadius(), paint);
        return null;
    }

    @Override
    public Void onStrokeColor(final StrokeColor c) { // when using the StrokeColor method
        final int defaultColor = c.getColor();
        paint.setColor(c.getColor());   // set color to the color of c
        c.getShape().accept(this);      // c has a shape variable, so we should probably use it
        // that also runs the method according to the correct shape
        // which also allows us to change the color back to the default
        paint.setColor(defaultColor);
        return null;
    }

    @Override
    public Void onFill(final Fill f) { // when using the fill method
        paint.setStyle(Style.FILL);   // set the style to fill
        f.getShape().accept(this);  // grab the shape that we want to fill and use visitor
        return null;                // to complete using the accept method
    }

    @Override
    public Void onGroup(final Group g) { // when using the group method, g = group of shapes
        // Since Group.java makes a list, we need to iterate through g and call them using accept
        // so that it is compatable with the Group Method.
        for (final Shape shape: g.getShapes()) {
            shape.accept(this);
        }
        return null;
    }

    @Override
    public Void onLocation(final Location l) {

        return null;
    }

    @Override
    public Void onRectangle(final Rectangle r) {

        return null;
    }

    @Override
    public Void onOutline(Outline o) {

        return null;
    }

    @Override
    public Void onPolygon(final Polygon s) {

        final float[] pts = null;

        canvas.drawLines(pts, paint);
        return null;
    }
}
