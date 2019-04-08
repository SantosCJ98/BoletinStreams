package clases;

public class UserTalk {

	static Database bd;
	
	UserTalk() {
		
		bd = new Database();
		
	}

	public static void main(String args[]) {

		bd.showLegalAgeStudentCount();

		System.out.println();

		bd.showStudentNamesOrderAlphabetically();

		System.out.println();

		bd.showFistTwoStudentsNames();

		System.out.println();

		bd.showStudentsNamesExceptTheFistOne();

		System.out.println();

		bd.showStudentsNamesUntilFirstNotLegalAgeOne();

		System.out.println();

		bd.showStudentsSinceFirstNotLegalAgeOne();

		System.out.println();

		bd.showDifferentSubjectsOrderedAlphabetically();

		System.out.println();

		bd.showStudentsGrantsAndSum();

		System.out.println();

		bd.getStudentsOlderThan20();

		System.out.println();

		bd.showYoungestStudentName();

		System.out.println();

		bd.showOldestStudentOlderThan23();

		System.out.println();

		bd.showStudentNamesWithCommasOrderedByAge();

		System.out.println();

		bd.showStudentCountInEachGroup();

		System.out.println();

		bd.showGrantSummary();

		System.out.println();

		bd.showAreAnyStudentUnderLegalAge();

		System.out.println();

		bd.showAllStudentHaveGrant();

		System.out.println();

		bd.showFirstStudentWithoutGrant();

		System.out.println();

		bd.showHowManyStudentWithOrWithoutGrant();

		System.out.println();

		bd.showNumberOfSubjectsOfEachStudent();

		System.out.println();

		bd.showNumberOfPassersStudentsOfEachSubject();

	}

}
