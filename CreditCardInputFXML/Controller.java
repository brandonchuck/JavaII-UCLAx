package CreditCardInputFXML;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Controller {

    // Controls to access from FXML file within the Controller class
    @FXML private TextField creditCardNumber;
    @FXML private TextField ccvNumber;
    @FXML private TextField expirationDate;
    @FXML private ImageView cardImgView;
    @FXML private ImageView cardStatusImgView;
    @FXML private ImageView ccvStatusImgView;

    Image green_check = new Image("CreditCardInput/cards/green-check.png", 20, 20, true, false);
    Image red_x = new Image("CreditCardInput/cards/red-x.png", 20, 20, true, false);



    // Constructor -- FXMLLoader will initialize all the TextFields and ImageViews
    public Controller(){
    }

    // Handle CreditCard Events
    public void handleCreditEvent(Event event) {
        this.setCreditCardDisplay();
    }

    // Handle CCV Events
    public void handleCCVEvent(Event event) {
        this.setCCVDisplay();
    }



    // ========== Get TextField Values ==========
    // Not all are used, but good to have for any additional features
    public String getCreditCardNumber(){
        return creditCardNumber.getText();
    }

    public String getCCVNumber(){
        return ccvNumber.getText();
    }

    public String getExpirationDateText(){
        return expirationDate.getText();
    }




    // ===== Get TextField Controls =====
    // Not all are used, but good to have for any additional features
    public TextField getCreditCard(){
        return creditCardNumber;
    }

    public TextField getCCV(){
        return ccvNumber;
    }

    public TextField getExpirationDate(){
        return expirationDate;
    }



    // ===== Get ImageView Controls =====
    public ImageView getCreditCardImageView(){
        return cardImgView;
    }

    public ImageView getCreditCardStatusImageView(){
        return cardStatusImgView;
    }

    public ImageView getCCVStatusImageView(){
        return ccvStatusImgView;
    }



    // Credit Card Validation
    public void setCreditCardDisplay() {

        // Card Images
        Image VISA_img = new Image("CreditCardInput/cards/visa-small.png", 75, 75, true, false);
        Image Master_img = new Image("CreditCardInput/cards/mastercard-small.png", 63, 63, true, false);
        Image Amex_img = new Image("CreditCardInput/cards/amex-small.png", 75, 75, true, false);
        Image Jcb_img = new Image("CreditCardInput/cards/jcb-small.png", 75, 75, true, false);


        // if card number goes over 16 digits, then card is invalid
        if (creditCardNumber.getText().length() < 17) {

            // if card is a VISA
            if (creditCardNumber.getText().startsWith("4")) {

                // show VISA image
                cardImgView.setImage(VISA_img);
                cardImgView.setVisible(true);
                cardStatusImgView.setVisible(true);

                // if card number is correct length
                if (creditCardNumber.getText().length() == 16) {
                    cardStatusImgView.setImage(green_check);

                    // if card number isn't the right length, or becomes and incorrect length
                } else {
                    cardStatusImgView.setImage(red_x);
                }
                System.out.println("This is a VISA");         // for debugging
            }


            // if card is a Mastercard
            else if (creditCardNumber.getText().startsWith("5")) {

                // Show Mastercard image
                cardImgView.setImage(Master_img);
                cardImgView.setVisible(true);
                cardStatusImgView.setVisible(true);

                // if card number is correct length
                if (creditCardNumber.getText().length() == 16) {
                    cardStatusImgView.setImage(green_check);

                    // if card number isn't the right length, or becomes and incorrect length
                } else {
                    cardStatusImgView.setImage(red_x);
                }
                System.out.println("This is a Mastercard");   // for debugging
            }


            // if card is a AMEX
            else if (creditCardNumber.getText().startsWith("34") || creditCardNumber.getText().startsWith("37")) {
                cardImgView.setImage(Amex_img);
                cardImgView.setVisible(true);
                cardStatusImgView.setVisible(true);

                // if card number is correct length
                if (creditCardNumber.getText().length() == 16) {
                    cardStatusImgView.setImage(green_check);

                    // if the card number isn't the right length, or becomes and incorrect length
                } else {
                    cardStatusImgView.setImage(red_x);
                }
                System.out.println("This is an Amex"); // for debugging
            }


            // if card is a JCB
            else if (creditCardNumber.getText().startsWith("35")) {
                cardImgView.setImage(Jcb_img);
                cardImgView.setVisible(true);
                cardStatusImgView.setVisible(true);

                if (creditCardNumber.getText().length() == 16) {
                    cardStatusImgView.setImage(green_check);

                    // if the card number isn't the right length, or becomes and incorrect length
                } else {
                    cardStatusImgView.setImage(red_x);
                }
                System.out.println("This is a JCB");          // for debugging
            }

            // if card number doesn't satisfy any card requirements
            else {
                // show red X for invalid cards and hide card image view
                cardStatusImgView.setImage(red_x);
                cardStatusImgView.setVisible(true);
                cardImgView.setVisible(false);

                // if text field becomes empty then hide check/X
                if (creditCardNumber.getText().isEmpty()) {
                    cardImgView.setVisible(false);
                    cardStatusImgView.setVisible(false);
                }
            }

            // credit card number is greater than 16 digits
        } else { // creditCardNumber.setText()
            creditCardNumber.setText("");
            cardImgView.setVisible(false);
            cardStatusImgView.setVisible(false);
            System.out.println("Invalid number, please try again");
        }

    }


    // CCV Validation
    public void setCCVDisplay() {

        // if CCV number goes over 4 its always invalid
        if (ccvNumber.getText().length() < 5) {

            // Check if credit card is an AMEX
            if (creditCardNumber.getText().startsWith("34") || creditCardNumber.getText().startsWith("37")) {

                // Check if CCV number is 4 digits
                if (ccvNumber.getText().length() == 4) {
                    this.validCCVStatus();
                    System.out.println("this is a valid AMEX CCV number");
                }

                // Check if CCV number isn't 4 digits for an AMEX card number
                else {
                    this.invalidCCVStatus();

                    // CCV number text field becomes empty
                    if (ccvNumber.getText().isEmpty()) {
                        ccvStatusImgView.setVisible(false);
                    }
                    System.out.println("this is not a valid AMEX CCV number");
                }

                // if credit card isn't an AMEX
            } else {

                // Check that CCV isn't 4 digits
                if (ccvNumber.getText().length() == 4) {
                    this.invalidCCVStatus();
                    System.out.println("this is not a valid CCV number");

                    // if CCV is 3 digits and NOT an AMEX card
                } else if (ccvNumber.getText().length() == 3) {
                    this.validCCVStatus();
                    System.out.println("this is a valid CCV number");
                }

                // if CCV isn't 3 or 4 digits
                else {
                    this.invalidCCVStatus();

                    // if CCV number text field becomes empty
                    if (ccvNumber.getText().isEmpty()) {
                        ccvStatusImgView.setVisible(false);
                    }
                    System.out.println("this is not a valid CCV number");
                }
            }

            // CCV number is greater than 4 digits
        } else {
            ccvNumber.setText("");
            ccvStatusImgView.setVisible(false);
            System.out.println("Not a valid CCV number");
        }
    }

    public void invalidCCVStatus(){
        ccvStatusImgView.setImage(red_x);
        ccvStatusImgView.setVisible(true);
    }

    public void validCCVStatus(){
        ccvStatusImgView.setImage(green_check);
        ccvStatusImgView.setVisible(true);
    }

}
