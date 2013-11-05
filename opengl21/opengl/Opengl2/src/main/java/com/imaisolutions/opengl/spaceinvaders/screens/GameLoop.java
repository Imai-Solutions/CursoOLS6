package com.imaisolutions.opengl.spaceinvaders.screens;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.imaisolutions.opengl.spaceinvaders.GameScreen;
import com.imaisolutions.opengl.spaceinvaders.Renderer;
import com.imaisolutions.opengl.spaceinvaders.SoundManager;
import com.imaisolutions.opengl.spaceinvaders.simulation.Simulation;
import com.imaisolutions.opengl.spaceinvaders.simulation.SimulationListener;
import com.imaisolutions.opengl.tools.GameActivity;

public class GameLoop implements GameScreen, SimulationListener
{
	public Simulation simulation;
	Renderer renderer;	
	SoundManager soundManager;

	public GameLoop( GL10 gl, GameActivity activity )
	{
		simulation = new Simulation();
		simulation.listener = this;
		renderer = new Renderer( gl, activity );
		soundManager = new SoundManager( activity );
	}
	
	public GameLoop(GL10 gl, GameActivity activity, Simulation simulation) 
	{
		this.simulation = simulation;
		this.simulation.listener = this;
		renderer = new Renderer( gl, activity );
		soundManager = new SoundManager( activity );
	}

	@Override
	public void render(GL10 gl, GameActivity activity) 
	{	
		renderer.render( gl, activity, simulation);
	}

	@Override
	public void update(GameActivity activity) 
	{	
		processInput( activity );
		simulation.update( activity.getDeltaTime() );
	}
	
	private void processInput( GameActivity activity )
	{		
		if( activity.getAccelerationOnYAxis() < 0 )
			simulation.moveShipLeft( activity.getDeltaTime(), Math.abs(activity.getAccelerationOnYAxis()) / 10 );
		else
			simulation.moveShipRight( activity.getDeltaTime(), Math.abs(activity.getAccelerationOnYAxis()) / 10 );
	
		
		if( activity.isTouched() )
			simulation.shot();
	}

	public boolean isDone( )
	{
		return simulation.ship.lives == 0;
	}
	
	public void dispose( )
	{
		renderer.dispose();
		soundManager.dispose();
	}

	@Override
	public void explosion() 
	{
		soundManager.playExplosionSound();
	}

	@Override
	public void shot() 
	{	
		soundManager.playShotSound();
	}
}
