package me.shadorc.discordbot.rpg;

import org.json.JSONObject;

import me.shadorc.discordbot.Storage;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

public class User {

	private final IGuild guild;
	private final IUser user;

	private int coins;

	public User(IGuild guild, IUser user) {
		this.guild = guild;
		this.user = user;
		this.coins = 0;
	}

	public User(IGuild guild, long id, JSONObject obj) {
		this.guild = guild;
		this.user = guild.getUserByID(id);
		this.coins = obj.getInt("coins");
	}

	public IGuild getGuild() {
		return guild;
	}

	public String getStringID() {
		return user.getStringID();
	}

	public int getCoins() {
		return coins;
	}

	public void addCoins(int gains) {
		this.coins += gains;
		this.save();
	}

	public String mention() {
		return user.mention();
	}

	public JSONObject toJSON() {
		JSONObject userJson = new JSONObject();
		userJson.put("coins", coins);
		return userJson;
	}

	private void save() {
		Storage.storeUser(this);
	}
}
