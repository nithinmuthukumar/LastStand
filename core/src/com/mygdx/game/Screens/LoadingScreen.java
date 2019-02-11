package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.LastStand;

public class LoadingScreen implements Screen {
    private final LastStand game;
    private final Label loadingLabel;

    public LoadingScreen(LastStand game) {
        loadingLabel = new Label("", game.style);
        loadingLabel.setPosition(400, 400);
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(game.batch.getProjectionMatrix()); //Needed for shapeRenderer to work alongside SpriteBatch
        shapeRenderer.setTransformMatrix(game.batch.getTransformMatrix());
        if (game.manager.update()) {
            //Loads file async, returns true when done
            if (game.manager.isLoaded("sprites/TOWER/WIZARD/wizard-tower-1.png") && game.manager.isLoaded("sprites/TOWER/WIZARD/wizard-tower-2.png") && game.manager.isLoaded("sprites/TOWER/WIZARD/wizard-tower-3.png") && game.manager.isLoaded("sprites/TOWER/WIZARD/wizard-tower-4.png")) {
                //Gdx seems to have weird issues with these files hmm.....
                game.initialize();
            } else {
                game.manager.load("sprites/TOWER/WIZARD/wizard-tower-1.png", Texture.class);
                game.manager.load("sprites/TOWER/WIZARD/wizard-tower-2.png", Texture.class);
                game.manager.load("sprites/TOWER/WIZARD/wizard-tower-3.png", Texture.class);
                game.manager.load("sprites/TOWER/WIZARD/wizard-tower-4.png", Texture.class);
            }
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); //Nice progress bar
        shapeRenderer.rect(100, 359, 824, 50); //Back of the bar
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(100, 359, 824 * game.manager.getProgress(), 50); //Progress!
        shapeRenderer.end();
//        loadingLabel.setText(Float.toString(game.manager.getProgress()));
//        game.batch.begin();
//        loadingLabel.draw(game.batch, 1);
//        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
