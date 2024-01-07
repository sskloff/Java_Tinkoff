package edu.project3.Models;

import java.time.OffsetDateTime;

public record Log(String remoteAddress, String remoteUser, OffsetDateTime timeLocal,
                  String method, String request, String protocol,
                  int status, long bodyBytesSent,
                  String httpReferer, String httpUserAgent) {
}
