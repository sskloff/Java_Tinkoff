package edu.project3;

import edu.project3.Models.Log;
import edu.project3.Models.ReportFileFormat;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    private final Map<Integer, Integer> responseCodes;
    private final Map<String, Integer> resources;
    private final ReportFileFormat format;
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private int requestsCount;
    private long totalResponseSize;
    private long averageResponseSize;
    private final Map<String, Integer> methods;
    private final Map<String, Integer> remoteAddresses;

    private Report(LogAnalyzer analyzer) {
        this.requestsCount = 0;
        this.totalResponseSize = 0L;
        this.averageResponseSize = 0L;
        this.responseCodes = new HashMap<>();
        this.resources = new HashMap<>();
        this.format = analyzer.getReportFormat();
        this.from = analyzer.getFrom();
        this.to = analyzer.getTo();
        //Статистики на доп баллы
        this.methods = new HashMap<>();
        this.remoteAddresses = new HashMap<>();
    }

    public static Report createLogReport(LogAnalyzer analyzer, List<Log> logs) {
        Report report = new Report(analyzer);
        for (Log log : logs) {
            if (log.timeLocal().isAfter(analyzer.getFrom())
                    && log.timeLocal().isBefore(analyzer.getTo())
            ) {
                report.requestsCount++;
                report.totalResponseSize += log.bodyBytesSent();

                int status = log.status();
                report.responseCodes.put(
                    status,
                    report.responseCodes.getOrDefault(status, 0) + 1
                );

                String request = log.request();
                report.resources.put(
                    request,
                    report.resources.getOrDefault(request, 0) + 1
                );

                String remoteAddress = log.remoteAddress();
                report.remoteAddresses.put(
                    remoteAddress,
                    report.remoteAddresses.getOrDefault(remoteAddress, 0) + 1
                );

                String method = log.method();
                report.methods.put(
                    method,
                    report.methods.getOrDefault(method, 0) + 1
                );
            }
        }
        report.averageResponseSize = report.totalResponseSize / report.requestsCount;
        return report;
    }

    public Map<Integer, Integer> getResponseCodes() {
        return responseCodes;
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public ReportFileFormat getFormat() {
        return format;
    }

    public long getRequestsCount() {
        return requestsCount;
    }

    public long getAverageResponseSize() {
        return averageResponseSize;
    }

    public OffsetDateTime getFrom() {
        return from;
    }

    public OffsetDateTime getTo() {
        return to;
    }

    public Map<String, Integer> getMethods() {
        return methods;
    }

    public Map<String, Integer> getRemoteAddresses() {
        return remoteAddresses;
    }
}
