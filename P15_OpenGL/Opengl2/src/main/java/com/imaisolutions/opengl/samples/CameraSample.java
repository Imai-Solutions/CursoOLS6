package com.imaisolutions.opengl.samples;

import android.opengl.GLU;
import android.os.Bundle;

import com.imaisolutions.opengl.tools.GameActivity;
import com.imaisolutions.opengl.tools.GameListener;
import com.imaisolutions.opengl.tools.Mesh;
import com.imaisolutions.opengl.tools.Mesh.PrimitiveType;

import javax.microedition.khronos.opengles.GL10;

public class CameraSample extends GameActivity implements GameListener 
{
    Mesh mesh;
	
	public void onCreate( Bundle savedInstance )
	{
		super.onCreate( savedInstance );
		setGameListener( this );
	}

	@Override
	public void setup(GameActivity activity, GL10 gl) 
	{	
		// we create a mesh with 3 vertices having different colors
		mesh = new Mesh( gl, 6, true, false, false );		
		
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 0f, -0.5f, -5 );
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 1f, -0.5f, -5 );
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 0.5f, 0.5f, -5 );
		
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( -0.5f, -0.5f, -2 );
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( 0.5f, -0.5f, -2 );
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( 0, 0.5f, -2);
	}
	
	@Override
	public void mainLoopIteration(GameActivity activity, GL10 gl)
	{
		gl.glViewport( 0, 0, activity.getViewportWidth(), activity.getViewportHeight() );
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gl.glMatrixMode( GL10.GL_PROJECTION );
		gl.glLoadIdentity();
		float aspectRatio = (float)activity.getViewportWidth() / activity.getViewportHeight();
		GLU.gluPerspective( gl, 90, aspectRatio, 1, 100 );

		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		GLU.gluLookAt( gl, 0, 1, -7, 0, 0, 0, 0, 1, 0 );

		mesh.render( PrimitiveType.Triangles );
	}


}
