//package org.example.utils;
//
//import org.apache.log4j.AppenderSkeleton;
//import org.apache.log4j.Layout;
//import org.apache.log4j.spi.LoggingEvent;
//import org.testng.Reporter;
//
//public class TestNgReportAppender extends AppenderSkeleton {
//    @Override
//    protected void append(LoggingEvent event) {
//        Reporter.log(eventToString(event));
//    }
//
//    private String eventToString(LoggingEvent event) {
//        final StringBuilder result = new StringBuilder(layout.format(event));
//
//        if (layout.ignoresThrowable()) {
//            final String[] s = event.getThrowableStrRep();
//            if (s != null) {
//                for (final String value : s) {
//                    result.append(value).append(Layout.LINE_SEP);
//                }
//            }
//        }
//        return result.toString();
//    }
//
//    @Override
//    public void close() {
//        throw new UnsupportedOperationException("The operation is not supported");
//    }
//
//    @Override
//    public boolean requiresLayout() {
//        return true;
//    }
//}