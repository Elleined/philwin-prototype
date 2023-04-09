# philwin-prototype
This Repository is a clone of Philwin a game that become popular in 2023

# To run this project 
  - Make sure to download javafx sdk in https://gluonhq.com/products/javafx/
  - Start a new project javafx project in IntelliJ 
    - File --> New --> Project --> JavaFX
  - And delete the module-info.java (if modular otherwise skip this part)
  - make sure to add this to you vm option 
  - before adding the vm  option make sure that javafx-sdk-XX.X.X.X\lib is match with the version you download
    - --module-path "C:\Program Files\Java\javafx-sdk-19.0.2.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics

# Additional Configuration
  - Download the plugin FXML Manager to help you bind Controller to your FXML 
  - To Automatic open the FXML in IntelliJ you can follow these steps
    - In the Settings dialog ( Ctrl+Alt+S ), select Languages & Frameworks | JavaFX. in the Path to SceneBuilder field 
    - Specificy the SceneBuilder.exe file this is located where your Scene Builder folder is downloaded

