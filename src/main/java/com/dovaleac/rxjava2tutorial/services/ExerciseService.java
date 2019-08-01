package com.dovaleac.rxjava2tutorial.services;

import com.dovaleac.rxjava2tutorial.domain.Character;
import com.dovaleac.rxjava2tutorial.domain.House;
import com.dovaleac.rxjava2tutorial.domain.Status;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Comparator;
import java.util.Map;

public class ExerciseService {

	/**
	 * Obtain the names of all the characters
	 *
	 * @param status The way to access everything
	 */
	public Flowable<String> getAllCharactersNames(Status status) {
		return status.getReadService().getAllCharacters()
			.map(Character::getName);
	}

	/**
	 * Obtain the names of all the characters and sort them by the number of letters they have
	 *
	 * @param status The way to access everything
	 */
	public Flowable<String> getAllCharactersNamesSortedByNumberOfLetters(Status status) {
		return getAllCharactersNames(status)
			.sorted(Comparator.comparing(String::length));
	}

	/**
	 * Obtain all the characters who possess at least one non-empty title. Note: some characters have only one title and
	 * it's empty. Those shouldn't be shown.
	 *
	 * @param status The way to access everything
	 */
	public Flowable<Character> getAllCharactersWithTitles(Status status) {
		return status.getReadService().getAllCharacters()
			.filter(character -> !character.getTitles().isEmpty())
			.filter(character -> !character.getTitles().get(0).isEmpty());
	}

	/**
	 * Obtain the character who has the most titles.
	 *
	 * @param status The way to access everything
	 */
	public Maybe<Character> getCharacterWithMostTitles(Status status) {
		return status.getReadService().getAllCharacters()
			.reduce((character1, character2) ->
				character1.getTitles().size() >= character2.getTitles().size() ? character1 : character2);
	}

	/**
	 * The same as the previous exercise, but returning a Single, as we know at least 1 character exists
	 *
	 * @param status The way to access everything
	 */
	public Single<Character> getCharacterWithMostTitlesAsSingle(Status status) {
		return status.getReadService().getAllCharacters()
			.reduce((character1, character2) ->
				character1.getTitles().size() >= character2.getTitles().size() ? character1 : character2)
			.toSingle();
	}

	/**
	 * Obtain a map that, for each house, obtains the length of its motto (the house's words)
	 *
	 * @param status The way to access everything
	 */
	public Single<Map<String, Integer>> getHousesWithMottoLength(Status status) {
		return status.getReadService().getAllHouses()
			.toMap(House::getName, house -> house.getWords().length());
	}

	/**
	 * Obtain all the characters who are lords of anything in the "Dorne" region
	 *
	 * @param status The way to access everything
	 */
	public Flowable<Character> getAllDornishLords(Status status) {
		return status.getReadService().getAllHouses()
			.filter(house -> house.getRegion().contains("Dorne"))
			.map(House::getCurrentLord)
			.flatMapMaybe(id -> status.getReadService().getCharacterById(id));
	}

	/**
	 * Get all the houses whose overlord house's overlord house is the parameterized one Note: please note the method
	 * getOverlordedByHouse in ReadService
	 *
	 * @param status The way to access everything
	 * @param house The house to search for
	 */
	public Flowable<House> getOverlordedsOverlorded(Status status, House house) {
		return null;
	}

	/**
	 * Create a map that for each dornish lord (remember we have an exercise in which you had to calculate all the
	 * dornish lords) specifies which % of all the titles among dornish lords s/he possesses. E.g: if there are only 2
	 * noblemen and both have 2 titles, both would have 50%
	 *
	 * @param status The way to access everything
	 */
	public Single<Map<String, Double>> getDornishLordsShareOfTitles(Status status) {

		return null;
	}

	/**
	 * In this exercise you have to add a new house, and then return that house's overlordeds' overlordeds (remember we
	 * have an exercise for this last part)
	 *
	 * @param status The way to access everything
	 * @param house The house to add
	 */
	public Flowable<House> getOverlordedOfNewHousesOverlorded(Status status, House house) {
		return null;
	}

	/**
	 * Add a new house, add a new character, set the character as the ruler of the house and then check the house's
	 * ruler
	 *
	 * @param status The way to access everything
	 * @param newHouse The house to add
	 * @param newRuler The house's new ruler
	 */
	public Single<Character> addHouseAndAddRulerAndCheckItsRuler(Status status, House newHouse,
		Character newRuler) {
		return null;
	}

}
