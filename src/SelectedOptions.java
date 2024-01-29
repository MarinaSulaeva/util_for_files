import java.util.ArrayList;
import java.util.List;

public class SelectedOptions {
    private String pathOutput;
    private String prefixOutput;
    private String pathInput;

    private boolean isShortStatistic;
    private boolean conditionPathOutput;
    private boolean conditionPrefix;
    private boolean conditionRewrite;
    private boolean conditionStatistic;
    private boolean conditionPathInput;
    private boolean conditionInfo;
    private List<String> fileNames;

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames.add(fileNames);
    }

    public SelectedOptions() {
        this.pathOutput = "";
        this.prefixOutput = "";
        this.pathInput = "";
        this.isShortStatistic = false;
        this.conditionPathOutput = false;
        this.conditionPrefix = false;
        this.conditionRewrite = true;
        this.conditionStatistic = false;
        this.conditionPathInput = false;
        this.conditionInfo = false;
        this.fileNames = new ArrayList<>();
    }

    public String getPathOutput() {
        return pathOutput;
    }

    public void setPathOutput(String pathOutput) {
        this.pathOutput = pathOutput;
    }

    public String getPrefixOutput() {
        return prefixOutput;
    }

    public void setPrefixOutput(String prefixOutput) {
        this.prefixOutput = prefixOutput;
    }

    public String getPathInput() {
        return pathInput;
    }

    public void setPathInput(String pathInput) {
        this.pathInput = pathInput;
    }

    public boolean isShortStatistic() {
        return isShortStatistic;
    }

    public void setShortStatistic(boolean shortStatistic) {
        isShortStatistic = shortStatistic;
    }

    public boolean isConditionPathOutput() {
        return conditionPathOutput;
    }

    public void setConditionPathOutput(boolean conditionPathOutput) {
        this.conditionPathOutput = conditionPathOutput;
    }

    public boolean isConditionPrefix() {
        return conditionPrefix;
    }

    public void setConditionPrefix(boolean conditionPrefix) {
        this.conditionPrefix = conditionPrefix;
    }

    public boolean isConditionRewrite() {
        return conditionRewrite;
    }

    public void setConditionRewrite(boolean conditionRewrite) {
        this.conditionRewrite = conditionRewrite;
    }

    public boolean isConditionStatistic() {
        return conditionStatistic;
    }

    public void setConditionStatistic(boolean conditionStatistic) {
        this.conditionStatistic = conditionStatistic;
    }

    public boolean isConditionPathInput() {
        return conditionPathInput;
    }

    public void setConditionPathInput(boolean conditionPathInput) {
        this.conditionPathInput = conditionPathInput;
    }

    public boolean isConditionInfo() {
        return conditionInfo;
    }

    public void setConditionInfo(boolean conditionInfo) {
        this.conditionInfo = conditionInfo;
    }
}
