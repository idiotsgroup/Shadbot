package me.shadorc.shadbot.data.lotto;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import discord4j.core.object.util.Snowflake;
import me.shadorc.shadbot.Config;
import me.shadorc.shadbot.utils.NumberUtils;

public class Lotto {

	@JsonProperty("historic")
	private LottoHistoric historic;
	@JsonProperty("jackpot")
	private AtomicInteger jackpot;
	@JsonProperty("gamblers")
	private List<LottoGambler> gamblers;

	public Lotto() {
		this.historic = null;
		this.jackpot = new AtomicInteger(0);
		this.gamblers = new CopyOnWriteArrayList<>();
	}

	public LottoHistoric getHistoric() {
		return historic;
	}

	public int getJackpot() {
		return jackpot.get();
	}

	public List<LottoGambler> getGamblers() {
		return gamblers;
	}

	public void setHistoric(LottoHistoric historic) {
		this.historic = historic;
	}

	public void addToJackpot(int coins) {
		int newPool = jackpot.get() + (int) Math.ceil(coins / 100f);
		jackpot.set(NumberUtils.between(newPool, 0, Config.MAX_COINS));
	}

	public void addGambler(Snowflake guildId, Snowflake userId, int number) {
		gamblers.add(new LottoGambler(guildId, userId, number));
	}

	public void resetJackpot() {
		jackpot.set(0);
	}

	public void resetGamblers() {
		gamblers.clear();
	}

}