package com.P.Client.view;

import com.P.Main;
import com.P.Client.controller.MainMenuController;
import com.P.Client.controller.RegisterController;
import com.P.Client.controller.StartController;
import com.P.Server.controller.Authorization;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.User;
import com.P.Client.model.GameAssetManager;
import com.P.common.model.Resualt;
import com.P.common.model.enums.SecurityQuestion;
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

public class SignupView implements Screen {
    // Register
    private Stage stage;
    private Stage forgetStage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Texture forgetTextture;
    private Image forgetImage;
    private TextField username;
    private TextField password;
    private TextField passwordConfirm;
    private TextField nickname;
    private TextField email;
    private TextButton registerButton;
    private TextButton loginButton;
    private TextButton randomPassButton;
    private TextButton backButton;
    private TextButton SignupButton;
    private SelectBox<String> genderBox;
    public Table table;
    public Table SQustionTable;
    private SelectBox<String> sQustion;
    private TextField answer;
    private TextField answerConfirm;
    private TextButton answerButton;
    private Label sQustionLabel;
    private Label message;
    private CheckBox stayLoggedIn;
    private TextButton confrim;
    private TextButton mainMenuButton;
    private TextButton forgetPasswordButton;
    public Table forgetTable;
    private TextField usernameForgetPassword;
    private TextButton forgetPasswordConfirm;
    private TextButton newPassword;
    private User user;

    private Label randomPassLabel;


    private RegisterController controller;

    public SignupView(RegisterController controller, Skin skin) {
        this.controller = controller;
        this.backgroundTexture = new Texture("background/signup.png");
        this.backgroundImage = new Image(backgroundTexture);
        this.forgetTextture = new Texture("background/forgetpassword.png");
        this.forgetImage = new Image(forgetTextture);
        this.username = new TextField("Your Username?", skin.get("login", TextField.TextFieldStyle.class));
        this.usernameForgetPassword = new TextField("Your Username?", skin.get("login", TextField.TextFieldStyle.class));
        this.password = new TextField("Your Password?", skin.get("password", TextField.TextFieldStyle.class));
        this.passwordConfirm = new TextField("Your Password Confirm?", skin.get("password", TextField.TextFieldStyle.class));
        this.nickname = new TextField("Your Nickname?", skin.get("login", TextField.TextFieldStyle.class));
        this.email = new TextField("Your Email?", skin);
        this.genderBox = new SelectBox<>(skin);
        this.message = new Label("", skin);
        this.randomPassLabel = new Label("", skin);
        this.sQustionLabel = new Label("", skin);
        genderBox.setItems("Male", "Female");
        this.answer = new TextField("Your Answer?", skin);
        this.answerConfirm = new TextField("Your Answer Confirm?", skin);
        this.sQustion = new SelectBox<>(skin);
        sQustion.setItems(SecurityQuestion.FAVORITE_QUSTION.string(), SecurityQuestion.FIRST_TEACHER.string());
        this.stayLoggedIn = new CheckBox("Stay Logged in?", skin.get("switch", CheckBox.CheckBoxStyle.class));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.FIREBRICK;
        style.overFontColor = Color.ORANGE;
        this.registerButton = new TextButton("Register", style);
        this.loginButton = new TextButton("Login", style);
        this.randomPassButton = new TextButton("RandomPass", style);
        this.backButton = new TextButton("Back", style);
        this.SignupButton = new TextButton("Signup", style);
        this.answerButton = new TextButton("Check", style);
        this.confrim = new TextButton("Confirm Login", style);
        this.mainMenuButton = new TextButton("Main Menu", style);
        this.forgetPasswordButton = new TextButton("Forget Password", style);
        this.newPassword = new TextButton("New Password", style);

        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.font = bigFont;
        style2.fontColor = Color.ORANGE;
        style2.overFontColor = Color.GREEN;
        this.forgetPasswordConfirm = new TextButton("Confirm", style2);

        this.table = new Table();
        this.SQustionTable = new Table();
        this.forgetTable = new Table();
        controller.setView(this);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        table.setFillParent(true);
        table.left();
        table.row().pad(10, 0, 10, 0);
        table.setPosition(100, 0);
        table.add(username).width(500).height(50);

        table.row().pad(2, 0, 2, 0);
        randomPassLabel.setFontScale(2);
        randomPassLabel.setColor(Color.BROWN);
        table.add(randomPassLabel).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(password).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(passwordConfirm).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(nickname).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(email).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(genderBox).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(message).width(500).height(50);

        stage.addActor(table);

        registerButton.setPosition(50, 100);
        stage.addActor(registerButton);

        loginButton.setPosition(400, 100);
        stage.addActor(loginButton);

        SignupButton.setPosition(630, 100);
        stage.addActor(SignupButton);

        randomPassButton.setPosition(880, 100);
        stage.addActor(randomPassButton);

        mainMenuButton.setPosition(1300, 100);
        stage.addActor(mainMenuButton);

        backButton.setPosition(1700, 100);
        stage.addActor(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.clear();
                Main.getMain().setScreen(new StartView(new StartController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt registerMessage = controller.handleRegister();
                message.setText(registerMessage.getAnswer());
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/IconStart.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = 40;
                BitmapFont bigFont = generator.generateFont(parameter);
                generator.dispose();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = bigFont;
                if (registerMessage.isAccept()) {
                    style.fontColor = Color.FOREST;
                } else {
                    style.fontColor = Color.RED;
                }
                message.setStyle(style);
                if (registerMessage.isAccept()) {
                    SQustionTable.setFillParent(true);
                    SQustionTable.center();
                    SQustionTable.row().pad(10, 0, 10, 0);
                    SQustionTable.setPosition(0, 0);
                    SQustionTable.add(sQustion).width(500).height(50);

                    SQustionTable.row().pad(10, 0, 10, 0);
                    SQustionTable.add(answer).width(500).height(50);

                    SQustionTable.row().pad(10, 0, 10, 0);
                    SQustionTable.add(answerConfirm).width(500).height(50);

                    SQustionTable.row().pad(10, 0, 10, 0);
                    SQustionTable.add(answerButton).width(500).height(50);

                    SQustionTable.row().pad(10, 0, 10, 0);
                    sQustionLabel.setFontScale(2);
                    sQustionLabel.setColor(Color.WHITE);
                    style.fontColor = Color.BROWN;
                    sQustionLabel.setStyle(style);
                    SQustionTable.add(sQustionLabel).width(500).height(50);


                    stage.addActor(SQustionTable);

                }
                message.setFontScale(2);
            }
        });

        randomPassButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String newPassword = Authorization.createRandomPassword();
                randomPassLabel.setText(newPassword);
            }
        });

        answerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt answer = controller.handlePickQuestion();
                sQustionLabel.setText(answer.getAnswer());
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.clear();
                SQustionTable.clear();
                table.setFillParent(true);
                table.center();
                table.row().pad(10, 0, 10, 0);
                table.setPosition(-100, 0);
                table.add(username).width(600).height(60);

                table.row().pad(10, 0, 10, 0);
                table.add(password).width(600).height(60);


                table.row().pad(10, 0, 10, 0);
                CheckBox.CheckBoxStyle style = new CheckBox.CheckBoxStyle(stayLoggedIn.getStyle());
                style.font.getData().setScale(2f);
                style.checkboxOff.setMinWidth(40);
                style.checkboxOff.setMinHeight(40);
                style.checkboxOn.setMinWidth(40);
                style.checkboxOn.setMinHeight(40);
                style.fontColor = Color.BROWN;
                stayLoggedIn.setStyle(style);
                table.add(stayLoggedIn).width(500).height(50);
                table.pack();

                table.row().pad(10, 0, 10, 0);
                table.add(confrim).width(600).height(60);

                table.row().pad(10, 0, 10, 0);
                table.add(forgetPasswordButton).width(600).height(60);

                stage.addActor(table);
            }
        });

        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.clear();
                SQustionTable.clear();

                forgetStage = new Stage(new ScreenViewport());
                Gdx.input.setInputProcessor(forgetStage);

                forgetImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                forgetStage.addActor(forgetImage);
                forgetTable.setFillParent(true);
                forgetTable.center();
                forgetTable.row().pad(10, 0, 10, 0);
                forgetTable.setPosition(0, 0);
                forgetTable.add(usernameForgetPassword).width(600).height(70);


                forgetTable.row().pad(10, 0, 10, 0);
                forgetTable.add(answer).width(600).height(70);

                forgetTable.row().pad(10, 0, 10, 0);
                forgetTable.add(forgetPasswordConfirm).width(600).height(70);

                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/IconStart.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = 50;
                BitmapFont bigFont = generator.generateFont(parameter);
                generator.dispose();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = bigFont;
                style.fontColor = Color.BROWN;
                message.setStyle(style);
                sQustionLabel.setStyle(style);
                randomPassLabel.setStyle(style);
                message.setPosition(100, 200);
                sQustionLabel.setPosition(100, 150);
                randomPassLabel.setPosition(100, 300);
                forgetStage.addActor(forgetTable);
                forgetStage.addActor(message);
                forgetStage.addActor(sQustionLabel);
                forgetStage.addActor(randomPassLabel);

            }
        });

        forgetPasswordConfirm.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt messageResualt = controller.handleForgetPassword();
                Resualt sQustionLabelResualt = controller.handleAnswer();
                message.setText(messageResualt.getAnswer());
                sQustionLabel.setText(sQustionLabelResualt.getAnswer());

                if (messageResualt.isAccept() && sQustionLabelResualt.isAccept()) {
                    forgetTable.clear();
                    message.setText("");
                    sQustionLabel.setText("");
                    password.setPosition(600, 600);
                    password.setWidth(500);
                    password.setHeight(70);
                    forgetStage.addActor(password);

                    passwordConfirm.setPosition(600, 500);
                    passwordConfirm.setWidth(500);
                    passwordConfirm.setHeight(70);
                    forgetStage.addActor(passwordConfirm);

                    newPassword.setPosition(600, 400);
                    randomPassButton.setPosition(600, 200);
                    forgetStage.addActor(newPassword);
                    forgetStage.addActor(randomPassButton);
                }
            }
        });

        newPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt check = controller.handlePasswordLogic(getPassword().getText(), getPasswordConfirm().getText());
                sQustionLabel.setText(check.getAnswer());
                if (check.isAccept()) {
                    controller.handleNewPassword();
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new SignupView(new RegisterController(), GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    forgetStage.addActor(sQustionLabel);
                }

            }
        });

        confrim.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt answer = controller.handleLogin();
                message.setText(answer.getAnswer());
                message.setPosition(550, 250);
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/IconStart.ttf"));
                FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                parameter.size = 50;
                BitmapFont bigFont = generator.generateFont(parameter);
                generator.dispose();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = bigFont;
                if (answer.isAccept()) {
                    style.fontColor = Color.FOREST;
                    App.setLoggedInUser(UserRepo.findUserByUsername(username.getText())); // CHECK
                } else {
                    style.fontColor = Color.RED;
                }
                message.setStyle(style);

                stage.addActor(message);
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // if(App.loggedInUser != null)
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin(),null));

            }
        });

        SignupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SignupView(new RegisterController(), GameAssetManager.getGameAssetManager().getSkin()));

            }
        });


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
        if (forgetStage != null) {
            forgetStage.act(delta);
            forgetStage.draw();
        }
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public TextField getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(TextField passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public TextField getNickname() {
        return nickname;
    }

    public void setNickname(TextField nickname) {
        this.nickname = nickname;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public SelectBox<String> getGenderBox() {
        return genderBox;
    }

    public void setGenderBox(SelectBox<String> genderBox) {
        this.genderBox = genderBox;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public RegisterController getController() {
        return controller;
    }

    public void setController(RegisterController controller) {
        this.controller = controller;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(TextButton registerButton) {
        this.registerButton = registerButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(TextButton loginButton) {
        this.loginButton = loginButton;
    }

    public TextButton getRandomPassButton() {
        return randomPassButton;
    }

    public void setRandomPassButton(TextButton randomPassButton) {
        this.randomPassButton = randomPassButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    public Table getSQustionTable() {
        return SQustionTable;
    }

    public void setSQustionTable(Table SQustionTable) {
        this.SQustionTable = SQustionTable;
    }

    public SelectBox<String> getsQustion() {
        return sQustion;
    }

    public void setsQustion(SelectBox<String> sQustion) {
        this.sQustion = sQustion;
    }

    public TextField getAnswer() {
        return answer;
    }

    public void setAnswer(TextField answer) {
        this.answer = answer;
    }

    public TextButton getAnswerButton() {
        return answerButton;
    }

    public void setAnswerButton(TextButton answerButton) {
        this.answerButton = answerButton;
    }

    public Label getMessage() {
        return message;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    public Label getRandomPassLabel() {
        return randomPassLabel;
    }

    public void setRandomPassLabel(Label randomPassLabel) {
        this.randomPassLabel = randomPassLabel;
    }

    public TextField getAnswerConfirm() {
        return answerConfirm;
    }

    public void setAnswerConfirm(TextField answerConfirm) {
        this.answerConfirm = answerConfirm;
    }

    public Label getsQustionLabel() {
        return sQustionLabel;
    }

    public void setsQustionLabel(Label sQustionLabel) {
        this.sQustionLabel = sQustionLabel;
    }

    public CheckBox getStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(CheckBox stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public TextButton getConfrim() {
        return confrim;
    }

    public void setConfrim(TextButton confrim) {
        this.confrim = confrim;
    }

    public Stage getForgetStage() {
        return forgetStage;
    }

    public void setForgetStage(Stage forgetStage) {
        this.forgetStage = forgetStage;
    }

    public Texture getForgetTextture() {
        return forgetTextture;
    }

    public void setForgetTextture(Texture forgetTextture) {
        this.forgetTextture = forgetTextture;
    }

    public Image getForgetImage() {
        return forgetImage;
    }

    public void setForgetImage(Image forgetImage) {
        this.forgetImage = forgetImage;
    }

    public TextButton getMainMenuButton() {
        return mainMenuButton;
    }

    public void setMainMenuButton(TextButton mainMenuButton) {
        this.mainMenuButton = mainMenuButton;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public void setForgetPasswordButton(TextButton forgetPasswordButton) {
        this.forgetPasswordButton = forgetPasswordButton;
    }

    public Table getForgetTable() {
        return forgetTable;
    }

    public void setForgetTable(Table forgetTable) {
        this.forgetTable = forgetTable;
    }

    public TextField getUsernameForgetPassword() {
        return usernameForgetPassword;
    }

    public void setUsernameForgetPassword(TextField usernameForgetPassword) {
        this.usernameForgetPassword = usernameForgetPassword;
    }

    public TextButton getForgetPasswordConfirm() {
        return forgetPasswordConfirm;
    }

    public void setForgetPasswordConfirm(TextButton forgetPasswordConfirm) {
        this.forgetPasswordConfirm = forgetPasswordConfirm;
    }
}
