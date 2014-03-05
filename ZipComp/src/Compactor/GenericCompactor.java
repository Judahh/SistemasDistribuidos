/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compactor;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class GenericCompactor extends Thread {

   protected final String size;
   protected final String quantity;
   protected final String type;
   protected String way;
   protected long time;
   protected int number;

   public int getNumber() {
      return number;
   }

   public long getTime() {
      return time;
   }

   public String getWay() {
      return way;
   }

   public String getSize() {
      return size;
   }

   public String getType() {
      return type;
   }

   public String getQuantity() {
      return quantity;
   }

   public GenericCompactor(String size, String quantity, String type) {
      this.size = size;
      this.quantity = quantity;
      this.type = type;
      this.way = "Generic";
      this.number = 5;
   }

   public GenericCompactor(String size, String quantity, String type, int number) {
      this.size = size;
      this.quantity = quantity;
      this.type = type;
      this.way = "Generic";
      this.number = number;
   }

   @Override
   public void run() {

   }
}
