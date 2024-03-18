package com.example.gestionusuarios;
import com.example.gestionusuarios.usuarios.Administrador;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Objects;

import  javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class HelloController {
   @FXML
   private Button guardarCambios;
   @FXML
   private AnchorPane scrollView;
   @FXML
   private TextField name,correo,password;
   @FXML
   private TextField nombreEditar ,correoEditar;
   @FXML
   private CheckBox adminEditar;

   @FXML
   private Label infoNombre,infoCorreo,infoPassword,infoAdmin,infoID;
   @FXML
   private CheckBox admin;
   @FXML
   private Pane formRegisterUser,formEditarUsuario,panelInfoUser;

   @FXML
   public int idUserEditar ;
   @FXML
   private ArrayList<Administrador> listUser = new ArrayList<>();

   @FXML
   public void OpenFormRegisterUser(){
      formRegisterUser.setVisible(true);
   }
   public void CLoseFormRegisterUser(){
      formRegisterUser.setVisible(false);
   }

   public void cerrarInfoUser(){
      panelInfoUser.setVisible(false);
   }
   public void cerrarEditar(){
      nombreEditar.setText("");
      correoEditar.setText("");
      admin.setSelected(adminEditar.isSelected());
      formEditarUsuario.setVisible(false);

   }


   public void verInfoUsuario(String nombre,String correo,String password,int id,boolean admin){
      infoNombre.setText(nombre);
      infoCorreo.setText(correo);
      infoID.setText(""+id);
      infoPassword.setText(password);
      if (admin){
         infoAdmin.setText("si");
      }else {
         infoAdmin.setText("no");
      }
      panelInfoUser.setVisible(true);
   }
   public void editarUsuario(String nombre,String correo,String password,int id){
      //asignarle los campos con los datos a editar
      nombreEditar.setText(nombre);
      correoEditar.setText(correo);
      for (Administrador usuario : listUser) {
         // Verificamos si el ID del usuario coincide con el ID buscado
         if (usuario.getId() == id) {
            admin.setSelected(usuario.getAdmin());
            break; // Salimos del bucle una vez que se haya eliminado el usuario
         }
      }
      this.idUserEditar=id;
      formEditarUsuario.setVisible(true);
   }


   public void renderizarUsuario(){
      double verticalPosition = 10.0; // Posición vertical inicial
      double verticalSpacing = 10.0; // Espacio vertical entre paneles

      for(Administrador usuario : listUser){
         Pane itemPanel = new Pane();
         itemPanel.setPrefWidth(610.0);
         itemPanel.setPrefHeight(85.0);
         itemPanel.setStyle("-fx-border-radius: 3; -fx-background-color: #ffffff;");
         itemPanel.setLayoutY(verticalPosition);
         itemPanel.setId(String.valueOf(usuario.getId()));

         Label nombreLabel = new Label(usuario.getNombre());
         nombreLabel.setLayoutX(52);
         nombreLabel.setLayoutY(14);

         Label correoLabel = new Label(usuario.getCorreo());
         correoLabel.setLayoutX(55);
         correoLabel.setLayoutY(53);

         Separator separator = new Separator();
         separator.setLayoutX(41);
         separator.setLayoutY(11);
         separator.setOrientation(Orientation.VERTICAL);
         separator.setPrefHeight(64);

         Label numeroLabel = new Label("" + usuario.getId());
         numeroLabel.setLayoutX(14);
         numeroLabel.setLayoutY(25);

         Button editarButton = new Button("editar");
         editarButton.setLayoutX(429);
         editarButton.setLayoutY(13);
         editarButton.setPrefHeight(62);
         editarButton.setPrefWidth(73);
         editarButton.setStyle("-fx-background-color: #FFEC70; -fx-border-radius: 3;");
         editarButton.setOnAction(event -> editarUsuario(usuario.getNombre(),usuario.getCorreo(),usuario.getPassword(),usuario.getId()));

         Button eliminarButton = new Button("eliminar");
         eliminarButton.setLayoutX(517);
         eliminarButton.setLayoutY(13);
         eliminarButton.setPrefHeight(62);
         eliminarButton.setPrefWidth(73);
         eliminarButton.setStyle("-fx-background-color: #E93535; -fx-border-radius: 3;");

         eliminarButton.setOnAction(event -> eliminarUsuario(usuario.getId()));
         Button verButton = new Button("ver");
         verButton.setLayoutX(342);
         verButton.setLayoutY(12);
         verButton.setPrefHeight(62);
         verButton.setPrefWidth(73);
         verButton.setStyle("-fx-background-color: #53DB73; -fx-border-radius: 3;");
         verButton.setOnAction(event -> verInfoUsuario(usuario.getNombre(),usuario.getCorreo(),usuario.getPassword(),usuario.getId(),usuario.getAdmin()));

         itemPanel.getChildren().addAll(nombreLabel, correoLabel, separator, numeroLabel, editarButton, eliminarButton, verButton);
         scrollView.getChildren().add(itemPanel);
         verticalPosition += itemPanel.getPrefHeight() + verticalSpacing;
      }
      // Ajustar la altura del AnchorPane para que todos los paneles sean visibles
      scrollView.setMinHeight(verticalPosition);

   }

   public  void saveEditUser(){
      // Iteramos sobre los elementos de la lista
      for (Administrador usuario : listUser) {
         // Verificamos si el ID del usuario coincide con el ID buscado
         if (usuario.getId() == idUserEditar) {
            // Eliminamos el usuario de la lista
            usuario.setCorreo(correoEditar.getText());
            usuario.setNombre(nombreEditar.getText());
            usuario.setAdmin(adminEditar.isSelected());
            break; // Salimos del bucle una vez que se haya eliminado el usuario
         }
      }
      scrollView.getChildren().clear();
      // Renderizamos los usuarios actualizados
      renderizarUsuario();

      cerrarEditar();
   }
   public void eliminarUsuario(int id) {
      // Iteramos sobre los elementos de la lista
      for (int i = 0; i < listUser.size(); i++) {
         // Verificamos si el ID del usuario coincide con el ID buscado
         if (listUser.get(i).getId() == id) {
            // Eliminamos el usuario de la lista
            listUser.remove(i);
            break; // Salimos del bucle una vez que se haya eliminado el usuario
         }
      }

      // Limpiamos los elementos del scrollView una vez que se hayan eliminado los usuarios
      scrollView.getChildren().clear();

      // Renderizamos los usuarios actualizados
      renderizarUsuario();
   }
   public void registerUser(){


      if (Objects.equals(name.getText(), "") ||
              Objects.equals(correo.getText(), "") ||
              Objects.equals(password.getText(), "")){

         //alertar de registro de usuario sin datos
         // Crear un cuadro de diálogo de alerta
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("Información");
         alert.setHeaderText(null);
         alert.setContentText("no se puede registrar un usuario sin datos!");

         // Agregar botón OK
         ButtonType buttonTypeOK = new ButtonType("OK");
         alert.getButtonTypes().setAll(buttonTypeOK);

         // Mostrar el cuadro de diálogo de alerta y esperar hasta que el usuario lo cierre
         alert.showAndWait();

      }else{
         if(admin.isSelected()){
            Administrador newUser = new Administrador(name.getText(),correo.getText(),password.getText(),admin.isSelected());
            listUser.add(newUser);
         }else {
            Administrador newUser = new Administrador(name.getText(),correo.getText(),password.getText());
            listUser.add(newUser);
         }
         //crear y agregar panel al panelScroll
         this.renderizarUsuario();

         name.setText("");
         correo.setText("");
         password.setText("");

         this.CLoseFormRegisterUser();
      }

   }



}