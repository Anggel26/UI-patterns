package navigation;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbNavigator {
    // List that stores each navigation segment in order
    private final List<String> crumbs;

    public BreadcrumbNavigator() {
        this.crumbs = new ArrayList<>();
        // Set default home segment
        this.crumbs.add("Inicio");
    }

    public BreadcrumbNavigator(String root) {
        this.crumbs = new ArrayList<>();
        // Set custom root segment
        this.crumbs.add(root);
    }

    // Replace current tab or section
    public void setTab(String tabName) {
        if (crumbs.size() > 1) {
            crumbs.subList(1, crumbs.size()).clear();
        }
        crumbs.add(tabName);
    }

    // Navigate deeper into a subcategory
    public void push(String section) {
        crumbs.add(section);
    }

    // Go back one level in the hierarchy
    public String pop() {
        if (crumbs.size() > 1) {
            return crumbs.remove(crumbs.size() - 1);
        }
        return crumbs.get(0);
    }

    // Reset navigation to the root item
    public void reset() {
        String root = crumbs.get(0);
        crumbs.clear();
        crumbs.add(root);
    }

    // Build hierarchical breadcrumb string representation
    public String getCurrentPath() {
        return String.join(" > ", crumbs);
    }

    // Return the list of current breadcrumb segments
    public List<String> getCrumbs() {
        return new ArrayList<>(crumbs);
    }
}
