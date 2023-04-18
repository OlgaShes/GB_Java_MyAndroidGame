package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.units.BaseHero;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, crossBowMan, mage, monk, peasantL, peasantR, rouge, sniper, spearMan, winLeft, winRight;
	Music music;
	Game game;
	int turnCount;
	
	@Override
	public void create () {
		turnCount = 1;
		game = new Game();
		game.setUnits(10);
		game.initTeams();

		batch = new SpriteBatch();
		fon = new Texture("fons/" +
				Fons.values()[new Random().nextInt(Fons.values().length)] + ".png");
		music = Gdx.audio.newMusic(Gdx.files.internal("music/" + Mus.values()[new Random().nextInt(Mus.values().length)] + ".mp3"));
		music.setLooping(true);
		music.setVolume(0.125f);
		music.play();
		winLeft = new Texture("fons/winLeft.png");
		winRight = new Texture("fons/winRight.png");
		crossBowMan = new Texture("personages/CrossBowMan.png");
		mage = new Texture("personages/Mage.png");
		monk = new Texture("personages/Monk.png");
		peasantL = new Texture("personages/Peasant.png");
		peasantR = new Texture("personages/Peasant2.png");
		rouge = new Texture("personages/Rouge.png");
		sniper = new Texture("personages/Sniper.png");
		spearMan = new Texture("personages/SpearMan.png");
	}

	@Override
	public void render () {


		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		if (game.checkLosing(game.greenTeam))
			batch.draw(winLeft, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		else if (game.checkLosing(game.blueTeam))
			batch.draw(winRight, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		else {
			if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
				game.gameTurn();
				Gdx.graphics.setTitle(String.valueOf("Ход № "+ turnCount++));
			}
			batch.draw(fon, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			for (int i = game.UNITS - 1; i >= 0; i--) {
				batch.setColor(1, 1, 1, 1);
				if (game.blueTeam.get(i).getState() == BaseHero.State.dead)
					batch.setColor(Color.RED);
				int x = game.blueTeam.get(i).position.y * Gdx.graphics.getWidth() / (game.UNITS + 2);
				int y = (game.blueTeam.get(i).position.x - 1) * Gdx.graphics.getHeight() / (game.UNITS + 2);
				switch (game.blueTeam.get(i).getInfo()) {
					case "Арбалетчик":
						batch.draw(crossBowMan, x, y);
						break;
					case "Волшебник":
						batch.draw(mage, x, y);
						break;
					case "Оруженосец":
						batch.draw(peasantL, x, y);
						break;
					case "Разбойник":
						batch.draw(rouge, x, y);
						break;
				}

				batch.setColor(1, 1, 1, 1);
				if (game.greenTeam.get(i).getState() == BaseHero.State.dead)
					batch.setColor(Color.RED);
				x = game.greenTeam.get(i).position.y * Gdx.graphics.getWidth() / (game.UNITS + 2);
				y = (game.greenTeam.get(i).position.x - 1) * Gdx.graphics.getHeight() / (game.UNITS + 2);
				switch (game.greenTeam.get(i).getInfo()) {
					case "Монах":
						batch.draw(monk, x, y);
						break;
					case "Снайпер":
						batch.draw(sniper, x, y);
						break;
					case "Оруженосец":
						batch.draw(peasantR, x, y);
						break;
					case "Копейщик":
						batch.draw(spearMan, x, y);
						break;
				}

			}
			batch.setColor(1, 1, 1, 1);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
	}
}
