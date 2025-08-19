package com.P.Client.view;

import com.P.Main;
import com.P.Client.controller.MainMenuController;
import com.P.Client.controller.ProfileController;
import com.P.common.model.Basics.App;
import com.P.Client.model.GameAssetManager;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.Resualt;
import com.P.common.model.enums.Avatar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ProfileView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;
    public Table bunttonTable;
    public Table fieldTable;
    private TextButton usernameChange;
    private TextButton passwordChange;
    private TextButton emailChange;
    private TextButton nicknameChange;
    private TextButton back;
    private TextField username;
    private TextField password;
    private TextField email;
    private TextField nickname;
    private Label message;
    private Texture avatarTexture;
    private Image avatarImage;
    private TextButton profileChange;
    private Texture profileTexture;
    private Image profileImage;
    private SelectBox<String> avatar;
    private TextButton backButton;

    private Label profileData;

    private ProfileController controller;

    public ProfileView(ProfileController controller, Skin skin) {
        this.controller = controller;
        this.backgroundTexture = new Texture("background/profilemenu.png");
        this.backgroundImage = new Image(backgroundTexture);
        this.profileTexture = new Texture("background/avatar.png");
        this.profileImage = new Image(profileTexture);
        this.profileData = new Label("", skin);
        this.avatarTexture = new Texture(App.loggedInUser.getAvatar().getIconPath());
        this.avatarImage = new Image(avatarTexture);

        this.username = new TextField("Change your username", skin);
        this.password = new TextField("Change your password", skin);
        this.email = new TextField("Change your email", skin);
        this.nickname = new TextField("Change your nickname", skin);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.BLUE;
        style.overFontColor = Color.LIME;
        this.usernameChange = new TextButton("Change", style);
        this.passwordChange = new TextButton("Change", style);
        this.emailChange = new TextButton("Change", style);
        this.nicknameChange = new TextButton("Change", style);


        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/IconStart.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 75;
        BitmapFont bigFont2 = generator2.generateFont(parameter2);
        generator2.dispose();
        Label.LabelStyle style2 = new Label.LabelStyle();
        style2.font = bigFont2;
        style2.fontColor = Color.FIREBRICK;
        this.message = new Label("", style2);


        FreeTypeFontGenerator generator3 = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter3.size = 100;
        BitmapFont bigFont3 = generator3.generateFont(parameter3);
        generator3.dispose();
        TextButton.TextButtonStyle style3 = new TextButton.TextButtonStyle();
        style3.font = bigFont3;
        style3.fontColor = Color.PURPLE;
        style3.overFontColor = Color.PINK;
        this.back = new TextButton("Back", style3);
        this.profileChange = new TextButton("Change Profile", style3);
        this.backButton = new TextButton("Back", style3);

        this.avatar = new SelectBox<>(skin);
        avatar.setItems("Avatar 1", "Avatar 2", "Avatar 3", "Avatar 4", "Avatar 5", "Avatar 6");


        this.table = new Table();
        this.bunttonTable = new Table();
        this.fieldTable = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        profileData.setText(controller.handleUserInfoQuery().getAnswer());
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Lighning Ceremony.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = bigFont;
        style.fontColor = Color.NAVY;
        profileData.setStyle(style);
        profileData.setPosition(100, 450);
        avatarImage.setPosition(100, 750);
        avatarImage.setSize(200, 200);
        stage.addActor(avatarImage);
        stage.addActor(profileData);

        fieldTable.setFillParent(true);
        fieldTable.right();
        fieldTable.row().pad(10, 0, 10, 0);
        fieldTable.setPosition(-100, 0);
        fieldTable.add(username).width(500).height(70);

        fieldTable.row().pad(10, 0, 10, 0);
        fieldTable.add(password).width(500).height(70);

        fieldTable.row().pad(10, 0, 10, 0);
        fieldTable.add(email).width(500).height(70);

        fieldTable.row().pad(10, 0, 10, 0);
        fieldTable.add(nickname).width(500).height(70);


        stage.addActor(fieldTable);


        bunttonTable.setFillParent(true);
        bunttonTable.right();
        bunttonTable.row().pad(10, 0, 10, 0);
        bunttonTable.setPosition(-500, 0);
        bunttonTable.add(usernameChange).width(500).height(70);

        bunttonTable.row().pad(10, 0, 10, 0);
        bunttonTable.add(passwordChange).width(500).height(70);

        bunttonTable.row().pad(10, 0, 10, 0);
        bunttonTable.add(emailChange).width(500).height(70);

        bunttonTable.row().pad(10, 0, 10, 0);
        bunttonTable.add(nicknameChange).width(500).height(70);

        stage.addActor(bunttonTable);

        back.setPosition(1500, 100);
        stage.addActor(back);

        profileChange.setPosition(800, 100);
        stage.addActor(profileChange);

        usernameChange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt resualt = controller.handleChangeUsername();

                if (resualt.isAccept()) {
                    table.clear();
                    Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    message.setText(resualt.getAnswer());
                    message.setPosition(100, 100);
                    stage.addActor(message);
                }
            }
        });

        passwordChange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt resualt = controller.handleChangePassword();

                if (resualt.isAccept()) {
                    table.clear();
                    Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    message.setText(resualt.getAnswer());
                    message.setPosition(100, 100);
                    stage.addActor(message);
                }
            }
        });

        emailChange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt resualt = controller.handleChangeEmail();

                if (resualt.isAccept()) {
                    table.clear();
                    Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    message.setText(resualt.getAnswer());
                    message.setPosition(100, 100);
                    stage.addActor(message);
                }
            }
        });

        nicknameChange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt resualt = controller.handleChangeNickname();

                if (resualt.isAccept()) {
                    table.clear();
                    Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    message.setText(resualt.getAnswer());
                    message.setPosition(100, 100);
                    stage.addActor(message);
                }
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.clear();
                Main.getMain().setScreen(new MainView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin(),null));

            }
        });

        profileChange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fieldTable.clear();
                stage.clear();
                profileImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                stage.addActor(profileImage);

                avatar.setPosition(100,600);
                avatar.setWidth(300);
                avatar.setHeight(70);
                avatar.setColor(Color.BLUE);
                stage.addActor(avatar);

                backButton.setPosition(100,400);
                stage.addActor(backButton);

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(getAvatar().getSelected().equals("Avatar 1")){
                    App.loggedInUser.setAvatar(Avatar.ELLIOTT);
                }else if(getAvatar().getSelected().equals("Avatar 2")){
                    App.loggedInUser.setAvatar(Avatar.HALEY);
                }else if(getAvatar().getSelected().equals("Avatar 3")){
                    App.loggedInUser.setAvatar(Avatar.LEAH);
                }else if(getAvatar().getSelected().equals("Avatar 4")){
                    App.loggedInUser.setAvatar(Avatar.ROBIN);
                }else if(getAvatar().getSelected().equals("Avatar 5")){
                    App.loggedInUser.setAvatar(Avatar.SEBASTIAN);
                }else if(getAvatar().getSelected().equals("Avatar 6")){
                    App.loggedInUser.setAvatar(Avatar.SHANE);
                }
                UserRepo.saveUser(App.loggedInUser);
                // Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
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

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getBunttonTable() {
        return bunttonTable;
    }

    public void setBunttonTable(Table bunttonTable) {
        this.bunttonTable = bunttonTable;
    }

    public Table getFieldTable() {
        return fieldTable;
    }

    public void setFieldTable(Table fieldTable) {
        this.fieldTable = fieldTable;
    }

    public TextButton getUsernameChange() {
        return usernameChange;
    }

    public void setUsernameChange(TextButton usernameChange) {
        this.usernameChange = usernameChange;
    }

    public TextButton getPasswordChange() {
        return passwordChange;
    }

    public void setPasswordChange(TextButton passwordChange) {
        this.passwordChange = passwordChange;
    }

    public TextButton getEmailChange() {
        return emailChange;
    }

    public void setEmailChange(TextButton emailChange) {
        this.emailChange = emailChange;
    }

    public TextButton getNicknameChange() {
        return nicknameChange;
    }

    public void setNicknameChange(TextButton nicknameChange) {
        this.nicknameChange = nicknameChange;
    }

    public TextButton getBack() {
        return back;
    }

    public void setBack(TextButton back) {
        this.back = back;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setPassword(TextField password) {
        this.password = password;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getNickname() {
        return nickname;
    }

    public void setNickname(TextField nickname) {
        this.nickname = nickname;
    }

    public Label getProfileData() {
        return profileData;
    }

    public void setProfileData(Label profileData) {
        this.profileData = profileData;
    }

    public ProfileController getController() {
        return controller;
    }

    public void setController(ProfileController controller) {
        this.controller = controller;
    }

    public SelectBox<String> getAvatar() {
        return avatar;
    }

    public void setAvatar(SelectBox<String> avatar) {
        this.avatar = avatar;
    }
}
