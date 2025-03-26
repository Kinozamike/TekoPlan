// === FILE: README.md ===
# 🚖 TekoPlan – Application Android de gestion des courses de taxi

TekoPlan est une application mobile Android moderne conçue pour les chauffeurs de taxi et VTC, leur permettant de suivre facilement leurs trajets, revenus, bilans et objectifs.

---

## ✨ Fonctionnalités principales

- 📍 Géolocalisation du point de départ et d’arrivée
- 💰 Validation du coût de chaque trajet
- 📊 Statistiques mensuelles, trimestrielles, annuelles
- 📈 Prédictions de revenus sur base historique
- 📅 Remise à zéro automatique des totaux à minuit
- 📋 Historique complet des courses
- 📊 Tableau de bord avec graphiques motivationnels
- 🔒 Stockage local sécurisé (Room)
- 💎 Abonnement mensuel via Google Play pour débloquer les fonctionnalités Premium

---

## 🛠️ Technologies utilisées

- Kotlin + Jetpack Compose
- ViewModel + StateFlow
- Room Database (stockage local)
- Google Play Billing
- Fused Location Provider (géolocalisation GPS)
- Navigation Compose
- Material Design 3

---

## 📁 Structure du projet

```
com.tekoplan
├── ui/screens           # Tous les écrans de l'app
├── ui/components        # Composants réutilisables (graphiques, boutons...)
├── viewmodel            # Logique métier & UI State
├── data/database        # DAO, Entités Room, Database
├── billing              # Abonnement Google Play
├── utils                # Fonctions utilitaires (dates, localisation...)
├── navigation           # Navigation Compose (routes, NavHost...)
```

---

## ▶️ Lancer l'application

1. Cloner le dépôt :
```bash
git clone https://github.com/Kinozamike/TekoPlan.git
```

2. Ouvrir le projet dans **Android Studio Giraffe** ou version ultérieure
3. Brancher un appareil Android réel ou un émulateur API 21+
4. Cliquer sur **Run > Run 'app'**

---

## 📦 Publication

- Générer un bundle `.aab` via **Build > Generate Signed Bundle / APK**
- Publier sur Google Play Console
- Créer un produit d’abonnement ID : `monthly_premium`
- Joindre une **politique de confidentialité** (voir `politique_confidentialite.md`)
- Ajouter icône `512x512`, captures d'écran, et description complète

---

## 📧 Contact

**Développeur :** Kinozamike  
**Email :** tekoplan@app.com

---

## 📄 Licence

Ce projet est sous licence **MIT** – voir le fichier `LICENSE` si présent.
