# 💩 Telegram Bathroom Bot 🧻

Welcome to the **Bathroom Bot**! This fun yet functional Telegram bot, built using Spring Boot, is designed to track the *most important* activity of your group members... yes, we're talking about bathroom visits. 🏆

Ever wondered who in your group is the King (or Queen) of the bathroom breaks? Look no further! The **Bathroom Bot** will record and rank each member based on how many times they go to the bathroom for a "serious" matter. You can check the leaderboard daily, monthly, or for all-time results. This is the ultimate solution to keep track of your group's "productivity"!

## 🚀 Features

- **Daily Leaderboard**: See who has been the most active today! 💪
- **Monthly Leaderboard**: Track bathroom activity across the month and identify long-term "champions". 📅
- **All-Time Leaderboard**: Find out who holds the record for the most visits over time! 🏅
- **Fun and light-hearted** competitive environment for your Telegram group.

## 🛠️ Technology Stack

- **Spring Boot**: The bot's backend is powered by the robust Spring Boot framework, ensuring scalability and flexibility.
- **Telegram Bot API**: Communication with Telegram is handled smoothly using the official Telegram Bot API.
- **Database**: The bot uses a Postgres relational database to store and track the *most valuable* metrics.

## 📦 Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- A registered Telegram Bot Token (get it from [BotFather](https://core.telegram.org/bots#botfather))

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/TizianoSciarpetta/bathroom-bot.git
    ```

2. Navigate to the project directory:

    ```bash
    cd bathroom-bot
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Rename and configure `application-example.properties` into `application.properties` file:

    ```properties
    telegram.bot.username=YOUR_TELEGRAM_BOT_USERNAME
    telegram.bot.token=YOUR_TELEGRAM_BOT_TOKEN
    spring.datasource.username=YOUR_DATABASE_USERNAME
    spring.datasource.password=YOUR_DATABASE_PASSWORD
    ```

5. Run the application:

    ```bash
    mvn spring-boot:run
    ```

### Usage

1. Add the bot to your Telegram group.
2. Members can log their *bathroom breaks* by sending the poop emoji (💩) as a message.
3. Use the appropriate buttons to check the daily, monthly, or all-time rankings.

## 🎯 How It Works

When a member sends the poop emoji, the bot records the entry with the current timestamp and stores it in the database. The leaderboards are dynamically updated, so everyone can keep an eye on the competition!

## 👑 Achievements

Become the **Master of the Throne** by topping the leaderboard! The bot encourages a friendly competition among group members.

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request if you have ideas to improve this bot or fix any bugs.

## 📄 License

This project is licensed under the MIT License.

---

**Disclaimer:** This bot is meant to be used for fun, and we don't take any responsibility for over-enthusiastic competition that may occur. Always poop responsibly! 🧻
