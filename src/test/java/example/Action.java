package example;

@FunctionalInterface
public interface Action {
  void invoke();

  static Runnable loop(
      final int count,
      final Action action
  ) {
    return new Runnable() {
      public void run() {
        for (int i = 1; i <= count; i++) {
          action.invoke();
        }
      }
    };
  }
}
