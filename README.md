📌 Mealio - README  

![pic1](https://github.com/user-attachments/assets/23f3491e-55be-4f1f-926f-a549f8ef2a82)       ![pic2](https://github.com/user-attachments/assets/fe272426-bb7a-4b43-9458-68cdb2925495)
![pic3](https://github.com/user-attachments/assets/67be6b34-d769-475f-a4b8-5f18e6365e45)       ![pic4](https://github.com/user-attachments/assets/21050401-c7cc-47fa-9799-d83a235a6612)
![pic5](https://github.com/user-attachments/assets/2a743f83-9417-4bb4-b895-e3aa4bfd7136)

🍽️ Overview  
Mealio is an Android application designed to help users **plan their weekly meals** effortlessly. With a seamless experience, users can **explore meal categories, search for specific dishes, receive meal suggestions, and save their favorite meals for offline access.**  

🔗 Powered by [TheMealDB API](https://themealdb.com/api.php).  
🛠️ Uses **Room Database** for offline storage and **Firebase** for authentication & data synchronization.  

Features  
🔹 Meal Discovery & Search  
- **Meal of the Day** – Get inspired by a randomly suggested meal.  
- **Advanced Search** – Find meals by:  
  - **Country**  
  - **Ingredient**  
  - **Category**  
- **Categories & Countries** – Browse popular meals based on categories or origins.  

🔹 Star & Offline Access  
- **Add or remove** meals from your Star.  
- **Offline Mode** – View saved favorite meals without an internet connection.   

🔹 Meal Planning  
- Plan meals for the current week by adding/removing meals.  
- Access planned meals even when offline.  

🔹 Authentication & Data Sync  
- **Login/Sign Up via:**  
  - Email & Password  
  - Google 
  - Guest Mode (browse categories, search, and view Meal of the Day)  
- **Cloud Backup:**  
  - Sync & restore saved meals upon login.  
  - Auto-login after first successful sign-in.
    
🔹 Meal Details  
Each meal includes:  
✅ Name + Image  
✅ Origin Country  
✅ Ingredients  
✅ Measurements  
✅ Step-by-step instructions  
✅ Embedded video tutorial
✅ Favorite button to add/remove the meal  


🛠️ Tech Stack  
- **Languages:** Java  
- **Architecture:** MVP
- Reactive Programming: RxJava
- **Database:** Room Database (for offline storage)  
- **Authentication & Sync:** Firebase Firestore & Firebase Authentication  
- **API:** TheMealDB  
- **UI Animation:** Lottie  

