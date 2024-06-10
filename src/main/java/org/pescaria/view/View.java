package org.pescaria.view;

public interface View {
	void startView();
}

public class MainView implements View {
	@Override
	public void startView() {
		System.out.println("Salve");
	}

}
