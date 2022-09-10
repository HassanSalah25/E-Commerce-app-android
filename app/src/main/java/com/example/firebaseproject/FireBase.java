package com.example.firebaseproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.*;

import java.util.ArrayList;

public class FireBase {

    private FirebaseStorage storageReference;
    private DatabaseReference x;       private StorageReference storageReference1;
    private StorageReference ref;      private ArrayList<User> csts;
    private Context context;           private ArrayList<User> admins;
    private UploadTask ut;             private ArrayList<Product> products;
    private FirebaseDatabase db;       private ArrayList<User> users;
    private String Turi;

    public FireBase(Context mcontext) {
        db = FirebaseDatabase.getInstance();
        x = db.getReference("contact");
        storageReference = FirebaseStorage.getInstance();
        storageReference1 = storageReference.getReference();
        users = new ArrayList<>();
        csts = new ArrayList<>();
        admins = new ArrayList<>();
        products = new ArrayList<>();
        context = mcontext;
        getSeller();
        getAdmin();
        getCustomer();
        getProduct();
    }


    private void getSeller(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot){
                users.clear();
                for (DataSnapshot singleSnapshot : snapshot.child("Seller").getChildren()) {
                    User user = singleSnapshot.getValue(User.class);
                    user.setKey(singleSnapshot.getKey());
                    users.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getCustomer(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.child("Customer").getChildren()) {
                    User cst = singleSnapshot.getValue(User.class);
                    cst.setKey(singleSnapshot.getKey());
                    csts.add(cst);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getAdmin(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.child("Admin").getChildren()) {
                    User admin = singleSnapshot.getValue(User.class);
                    admin.setKey(singleSnapshot.getKey());
                    admins.add(admin);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getProduct(){
        x.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot singleSnapshot : snapshot.child("Product").getChildren()) {
                    Product product = singleSnapshot.getValue(Product.class);
                    product.setKey(singleSnapshot.getKey());
                    products.add(product);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String generateKey() {
        return x.push().getKey();
    }

    public ArrayList<User> getSellers() {
        return users;
    }

    public ArrayList<User> getCustomers() {
        return csts;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public void setSeller(String Name,String UName, String password, String phone, String location, String state,String product) {
        String key = generateKey();
        User user = new User(Name,UName,password,phone,Turi,location,state,product);
        x.child("Seller").child(key).setValue(user);
    }

    public void setCustomer(String Name, String UName,String password, String phone, String state) {
        String key = generateKey();
        User cst = new User(Name,UName,password,phone,Turi,null,state,null);
        x.child("Customer").child(key).setValue(cst);
    }

    public void setAdmin(String Name, String password) {
        String key = generateKey();
        User admn = new User(Name,null,password,null,null,null,null,null);
        x.child("Admin").child(key).setValue(admn);
    }

    public void setProduct(String Name, String price, String sale, String type, String state) {
        String key = generateKey();
        Product prdct = new Product(Name,price,sale,Turi,state,type);
        x.child("Product").child(key).setValue(prdct);
    }

    public void removeSeller(int pos){
        x.child("Seller").child(getSellers().get(pos).getKey()).removeValue();
    }

    public void removeCustomer(int pos){
        x.child("Customer").child(getSellers().get(pos).getKey()).removeValue();
    }

    public void removeAdmin(int pos){
        x.child("Admin").child(getSellers().get(pos).getKey()).removeValue();
    }

    public void removeProduct(int pos){
        x.child("Product").child(getSellers().get(pos).getKey()).removeValue();
    }

    public void uploadImage(Uri filePath) {
        if (filePath != null) {
            ref = storageReference1.child("images/" + filePath.getLastPathSegment());
            ut = ref.putFile(filePath);
            ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            ut.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Turi = uri.toString();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                            Toast.makeText(context, "Image couldn't Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }

    }
}

