var debugEnabled = true;
var winningMessage = "You won in the game of odds";
var winMultiplier = 15;
var winnings;
var name = "inburst";
var amount = 10;
var coins;
var goldDrop;
var player = {};
var currentEnemy = "rat";
var enemyGold;
var rat = {
    coinsDrop: 10,
    attack: 4,
    health: 20
}
var goblin = {
    coinsDrop: 20,
    attack: 8,
    health: 30
}

debug(rat);

function localStore(key, obj) {
    return window.localStorage.setItem(key, JSON.stringify(obj));
}

function localGet(key) {
    return JSON.parse(window.localStorage.getItem(key));
}

function debug(msg) {
    if (debugEnabled) {
        console.log(msg);
    }
}

function toTitleCase(str) {
    return str.replace(/\w\S*/g, function(txt) { return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase(); });
}

function loadPlayer() {
    name = player.name;
    coins = player.coins;
    gambleAttempts = player.gambleAttempts;
    attack = player.attack;
    health = player.health;
    maxHealth = player.maxHealth;
    debug("Loaded Player " + name + " Coins = " + coins + " You have gambled " + gambleAttempts + " times. Attack = " + attack + " Health = " + health + "/" + maxHealth)
}

function savePlayer() {
    localStore(name, {
        name: name,
        coins: coins,
        gambleAttempts: gambleAttempts,
        attack: 10,
        health: 100,
        maxHealth: 100
    });
}

function gamble(amount) {

    if (coins >= amount) {
        //var guess = prompt("Please enter a number 1-10");
        var guess = Math.floor(Math.random() * 10);
        var answer = Math.floor(Math.random() * 10);

        debug(guess);
        debug(answer);
        winnings = amount * winMultiplier;
        coins = coins - amount;
        gambleAttempts++;
        if (guess == answer) {
            coins = coins + winnings;
            console.log(name + " " + winningMessage + " + " + winnings + ". You now have " + coins + " coins.")

        } else {
            debug("You lost the Bet. Please try again. You have " + coins + " remaining");
        }
    } else {
        debug("Please get some more coins and try again");
    }
    savePlayer();

}

function attackEnemy(enemy) {
    damage = Math.floor(Math.random() * attack);
    return damage;

}

function getNpc(currentEnemy) {
    debug(rat);
    debug([currentEnemy]);
    debug([currentEnemy].attack);
    enemyAttack = [currentEnemy].attack;
    enemyHealth = [currentEnemy].health;
    enemyGold = [currentEnemy].coinsDrop;
    debug(enemyAttack);
}

function fightNpc(currentEnemy) {
    getNpc(currentEnemy);
    while (enemyHealth > 0 && health > 0) {
        var damage = attackEnemy(enemy);
        debug(damage);
        enemyHealth = enemyHealth - damage;
        if (enemyHealth < 0) { enemyHealth = 0; }
        debug("You hit the " + currentEnemy + " for " + damage + " damage. " + enemyHealth + " remaining.");
    }
    debug("You have killed the " + currentEnemy + " and have received " + getDrops() + " coins")
}

function getDrops() {
    goldDrop = Math.floor(Math.random() * enemyGold);
    debug(goldDrop);
    return goldDrop;
}

$(function() {
    $('#gamble').on('click', function() {
        gamble(amount);
    });
    $('#fight').on('click', function() {
        fightNpc(currentEnemy);
    });
    player = JSON.parse(localStorage.getItem(name));
    debug(player);
    loadPlayer();
});